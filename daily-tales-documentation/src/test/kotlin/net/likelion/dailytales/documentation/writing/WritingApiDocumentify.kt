package net.likelion.dailytales.documentation.writing

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.infrastructure.security.LoggedInUserArgumentResolver
import net.likelion.dailytales.core.domain.user.User
import net.likelion.dailytales.core.domain.writing.Visibility
import net.likelion.dailytales.core.domain.writing.Writing
import net.likelion.dailytales.core.domain.writing.WritingCommentary
import net.likelion.dailytales.writing.api.controller.WritingController
import net.likelion.dailytales.writing.application.PagedSimpleWritingDto
import net.likelion.dailytales.writing.application.SimpleWritingDto
import net.likelion.dailytales.writing.application.WriterDto
import net.likelion.dailytales.writing.application.WritingManagementService
import net.likelion.dailytales.writing.application.WritingService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class WritingApiDocumentify : Documentify() {
    @Mock
    lateinit var writingManagementService: WritingManagementService

    @Mock
    lateinit var writingService: WritingService

    @Mock
    lateinit var argumentResolver: LoggedInUserArgumentResolver

    @InjectMocks
    lateinit var api: WritingController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(api), emptyList(), listOf(argumentResolver))
        `when`(argumentResolver.supportsParameter(any())).thenReturn(true)
        `when`(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn("userId")
    }

    @Test
    fun getWritingApi() {
        `when`(writingManagementService.getWriting(any(), any())).thenReturn(stubWriting())

        documentation("글 조회 API") {
            information {
                summary("글 조회 API")
                description("글 조회 API 입니다.")
                tag("글")
            }
            requestSchema("글 조회 요청")
            requestLine(Method.GET, "/api/writings/{writingId}") {
                pathVariable("writingId", "글 ID", "writingId")
            }
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            responseSchema("글 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("title", "글 제목", "title")
                field("visibility", "글 공개 여부", "visibility")
                field("keywords", "키워드", listOf("keyword1", "keyword2"))
                field("content", "글 내용", "content")
                field("commentary", "감상문", "commentary")
                field("written_at", "작성일", "created_at")
            }
        }
    }

    @Test
    fun getWritingsApi() {
        `when`(writingService.getWritingsExcludingUser(any(), anyInt(), anyInt())).thenReturn(stubPagedWritings())

        documentation("다른 글 목록 조회 API") {
            information {
                summary("다른 글 목록 조회 API")
                description("다른 사람의 글 목록을 조회합니다. (PUBLIC 글만 조회 가능)")
                tag("글")
            }
            requestSchema("다른 글 목록 조회 요청")
            requestLine(Method.GET, "/api/writings") {
                queryParameter("page", "페이지 번호 (기본 값 0)", "0")
                queryParameter("size", "페이지 크기 (기본 값 10)", "10")
            }
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            responseSchema("다른 글 목록 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("page", "페이지 번호", 0)
                field("size", "페이지 크기", 10)
                field("total_elements", "전체 글 수", 20)
                field("total_pages", "전체 페이지 수", 2)
                field("contents[].id", "글 ID", "writing_id")
                field("contents[].title", "글 제목", "title")
                field("contents[].writer.id", "작성자 ID", "writer_id")
                field("contents[].writer.nickname", "작성자 닉네임", "nickname")
                field("contents[].writer.profile_image_url", "작성자 프로필", "www.example.com/profile.jpg")
                field("contents[].written_at", "작성일", "2024-07017")
            }
        }
    }

    private fun stubWriting(): Writing {
        return Writing(
            "id",
            User(
                "userId",
                "displayId",
                "nickname",
                "profileImageUrl"
            ),
            "title",
            Visibility.PUBLIC,
            listOf("keyword1", "keyword2"),
            "content",
            WritingCommentary.of("commentary"),
            LocalDateTime.now()
        )
    }

    private fun stubPagedWritings(): PagedSimpleWritingDto {
        val now = LocalDateTime.now()
        val contents = listOf(
            SimpleWritingDto(
                "writing_id1",
                "title1",
                stubWriter(),
                null,
                now
            ),
            SimpleWritingDto(
                "writing_id2",
                "title2",
                stubWriter(),
                null,
                now
            ),
            SimpleWritingDto(
                "writing_id3",
                "title3",
                stubWriter(),
                null,
                now
            )
        )
        return PagedSimpleWritingDto(
            0,
            10,
            20,
            2,
            contents
        )
    }

    private fun stubWriter(): WriterDto {
        return WriterDto(
            "writer_id",
            "nickname",
            "www.example.com/profile.jpg"
        )
    }
}