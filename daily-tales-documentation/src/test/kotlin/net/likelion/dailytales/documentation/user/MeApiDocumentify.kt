package net.likelion.dailytales.documentation.user

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.infrastructure.security.LoggedInUserArgumentResolver
import net.likelion.dailytales.core.domain.user.User
import net.likelion.dailytales.user.api.controller.MeController
import net.likelion.dailytales.user.application.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider

@ExtendWith(MockitoExtension::class)
class MeApiDocumentify : Documentify() {
    @Mock
    lateinit var service: UserService

    @Mock
    lateinit var argumentResolver: LoggedInUserArgumentResolver

    @InjectMocks
    lateinit var api: MeController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(api), emptyList(), listOf(argumentResolver))
        `when`(argumentResolver.supportsParameter(any())).thenReturn(true)
        `when`(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn("userId")
    }

    @Test
    fun meApi() {
        `when`(service.getUserById(any())).thenReturn(stub())

        documentation("내 정보 API") {
            information {
                summary("내 정보 API")
                description("내 정보 API 입니다.")
                tag("내 정보")
            }
            requestSchema("내 정보 요청")
            requestLine(Method.GET, "/api/me")
            requestHeaders {
                header("Authorization", "token", "access_token")
            }
            responseSchema("내 정보 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("display_id", "사용자 ID (표시용)", "display_id")
                field("nickname", "닉네임", "nickname")
                field("profile_image_url", "프로필 이미지 주소", "url")
            }
        }
    }

    @Test
    fun updateDisplayIdApi() {
        doNothing().`when`(service).updateDisplayId(any(), any())

        documentation("사용자 ID 수정 API") {
            information {
                summary("사용자 ID 수정 API")
                description("사용자 ID 수정 API 입니다.")
                tag("내 정보")
            }
            requestSchema("사용자 ID 수정 요청")
            requestLine(Method.PATCH, "/api/me/display-id")
            requestBody {
                field("display_id", "사용자 ID", "display_id")
            }
            responseLine(HttpStatus.OK)
        }
    }

    @Test
    fun updateNicknameApi() {
        doNothing().`when`(service).updateNickname(any(), any())

        documentation("닉네임 수정 API") {
            information {
                summary("닉네임 수정 API")
                description("닉네임 수정 API 입니다.")
                tag("내 정보")
            }
            requestSchema("닉네임 수정 요청")
            requestLine(Method.PATCH, "/api/me/nickname")
            requestBody {
                field("nickname", "닉네임", "nickname")
            }
            responseLine(HttpStatus.OK)
        }
    }

    private fun stub(): User {
        return User(
            "id",
            "display_id",
            "nickname",
            "www.example.com/profile.jpg"
        )
    }
}