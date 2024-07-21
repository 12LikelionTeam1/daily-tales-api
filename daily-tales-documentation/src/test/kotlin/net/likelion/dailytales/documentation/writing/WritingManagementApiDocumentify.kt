package net.likelion.dailytales.documentation.writing

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.infrastructure.security.LoggedInUserArgumentResolver
import net.likelion.dailytales.core.domain.writing.Visibility
import net.likelion.dailytales.writing.api.controller.WritingManagementController
import net.likelion.dailytales.writing.application.SimpleWritingDto
import net.likelion.dailytales.writing.application.WritingManagementService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import java.time.LocalDate
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class WritingManagementApiDocumentify : Documentify() {
    @Mock
    lateinit var service: WritingManagementService

    @Mock
    lateinit var argumentResolver: LoggedInUserArgumentResolver

    @InjectMocks
    lateinit var api: WritingManagementController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(api), emptyList(), listOf(argumentResolver))
        `when`(argumentResolver.supportsParameter(any())).thenReturn(true)
        `when`(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn("userId")
    }

    @Test
    fun registerWritingApi() {
        doNothing().`when`(service).registerWriting(any())

        documentation("글 등록 API") {
            information {
                summary("글 등록 API")
                description("글 등록 API 입니다.")
                tag("내 글 관리")
            }
            requestSchema("글 작성 요청")
            requestLine(Method.POST, "/api/me/writings")
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            requestBody {
                field("title", "글의 제목", "title")
                field("content", "글의 내용", "content")
                field("keywords", "글의 키워드", listOf("keyword1", "keyword2"))
                field("commentary", "감상문", "commentary")
            }
            responseLine(HttpStatus.OK)
        }
    }

    @Test
    fun updateWritingVisibilityApi() {
        doNothing().`when`(service).updateVisibility(any(), any(), any())

        documentation("글 공유 범위 수정 API") {
            information {
                summary("글 공유 범위 수정 API")
                description(
                    """
                    글 공유 범위 수정 API 입니다.
                    공유 범위 : ${Visibility.entries.joinToString(", ") { it.name }}
                    """.trimIndent()
                )
                tag("내 글 관리")
            }
            requestSchema("글 공유 범위 수정 요청")
            requestLine(Method.PATCH, "/api/me/writings/{id}/visibility") {
                pathVariable("id", "글 ID", "writing_id")
            }
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            requestBody {
                field("visibility", "글의 공유 범위", Visibility.PUBLIC)
            }
            responseLine(HttpStatus.OK)
        }
    }

    @Test
    fun updateWritingCommentaryApi() {
        doNothing().`when`(service).updateCommentary(any(), any(), any())

        documentation("글 감상문 수정 API") {
            information {
                summary("글 감상문 수정 API")
                description(
                    """
                    글 감상문 수정 API 입니다.
                    """.trimIndent()
                )
                tag("내 글 관리")
            }
            requestSchema("감상문 수정 요청")
            requestLine(Method.PATCH, "/api/me/writings/{id}/commentary") {
                pathVariable("id", "글 ID", "writing_id")
            }
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            requestBody {
                field("commentary", "감상문", "commentary")
            }
            responseLine(HttpStatus.OK)
        }
    }

    @Test
    fun getWritingsApi() {
        `when`(service.getWritingsOfUser(any(), any(), any())).thenReturn(stub())

        documentation("글 목록 조회 API") {
            information {
                summary("글 목록 조회 API")
                description(
                    """
                    글 목록 조회 API 입니다.
                    """.trimIndent()
                )
                tag("내 글 관리")
            }
            requestLine(Method.GET, "/api/me/writings") {
                queryParameter("start-date", "시작 날짜 (기본값 : 오늘)", LocalDate.now().toString())
                queryParameter("end-date", "종료 (기본값 : 오늘", LocalDate.now().toString())
            }
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            responseSchema("글 목록 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("writings[].id", "글 ID", "writing_id")
                field("writings[].title", "글 제목", "title")
                field("writings[].visibility", "글 공개 범위", Visibility.PUBLIC)
                field("writings[].written_at", "글 작성일", LocalDate.now().toString())
            }
        }
    }

    private fun stub() : List<SimpleWritingDto> {
        val now = LocalDateTime.now()
        return listOf(
            SimpleWritingDto(
                "writing_id1",
                "title1",
                null,
                Visibility.PUBLIC,
                now
            ),
            SimpleWritingDto(
                "writing_id2",
                "title2",
                null,
                Visibility.PUBLIC,
                now
            ),
            SimpleWritingDto(
                "writing_id3",
                "title3",
                null,
                Visibility.PRIVATE,
                now
            )
        )
    }
}