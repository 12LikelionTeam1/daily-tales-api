package net.likelion.dailytales.documentation.writing

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.infrastructure.security.LoggedInUserArgumentResolver
import net.likelion.dailytales.writing.api.controller.WritingCollectionController
import net.likelion.dailytales.writing.application.SimpleWritingDto
import net.likelion.dailytales.writing.application.WriterDto
import net.likelion.dailytales.writing.application.WritingCollectionService
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
class WritingCollectionApiDocumentify : Documentify() {
    @Mock
    lateinit var service: WritingCollectionService

    @Mock
    lateinit var argumentResolver: LoggedInUserArgumentResolver

    @InjectMocks
    lateinit var api: WritingCollectionController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(api), emptyList(), listOf(argumentResolver))
        `when`(argumentResolver.supportsParameter(any())).thenReturn(true)
        `when`(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn("userId")
    }

    @Test
    fun registerWritingToCollectionApi() {
        doNothing().`when`(service).registerWriting(any(), any())

        documentation("문집 글 등록 API") {
            information {
                summary("문집 글 등록 API")
                description("문집에 글을 등록하는 API 입니다.")
                tag("문집")
            }
            requestSchema("글 컬렉션에 글 등록 요청")
            requestLine(Method.POST, "/api/me/writing-collection")
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            requestBody {
                field("writing_id", "글 ID", "writingId")
            }
            responseSchema("글 컬렉션에 글 등록 응답")
            responseLine(HttpStatus.OK)
        }
    }

    @Test
    fun getWritingCollectionApi() {
        `when`(service.getCollections(any())).thenReturn(stubWritings())

        documentation("문집 글 목록 조회 API") {
            information {
                summary("문집 글 목록 조회 API")
                description("문집에 등록된 글 목록을 조회하는 API 입니다.")
                tag("문집")
            }
            requestSchema("문집 글 목록 조회 요청")
            requestLine(Method.GET, "/api/me/writing-collection")
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            responseSchema("문집 글 목록 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("collection[].id", "글 ID", "writing_id")
                field("collection[].title", "글 제목", "title")
                field("collection[].writer.id", "작성자 ID", "writer_id")
                field("collection[].writer.nickname", "작성자 닉네임", "nickname")
                field("collection[].writer.profile_image_url", "작성자 프로필", "www.example.com/profile.jpg")
                field("collection[].written_at", "글 작성일", LocalDate.now().toString())
            }
        }
    }

    @Test
    fun removeWritingFromCollectionApi() {
        doNothing().`when`(service).removeWriting(any(), any())

        documentation("문집 글 삭제 API") {
            information {
                summary("문집 글 삭제 API")
                description("문집에서 글을 삭제하는 API 입니다.")
                tag("문집")
            }
            requestSchema("문집 글 삭제 요청")
            requestLine(Method.DELETE, "/api/me/writing-collection/{id}") {
                pathVariable("id", "글 ID", "writing_id")
            }
            requestHeaders {
                header("Authorization", "액세스 토큰", "access_token")
            }
            responseLine(HttpStatus.OK)
        }
    }

    private fun stubWritings(): List<SimpleWritingDto> {
        val now = LocalDateTime.now()
        return listOf(
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
    }

    private fun stubWriter(): WriterDto {
        return WriterDto(
            "writer_id",
            "nickname",
            "www.example.com/profile.jpg"
        )
    }
}