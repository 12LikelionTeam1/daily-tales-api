package net.likelion.dailytales.documentation.authentication

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.api.controller.AuthController
import net.likelion.dailytales.authentication.application.service.AuthService
import net.likelion.dailytales.core.domain.authentication.Token
import net.likelion.dailytales.core.domain.authentication.TokenType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider

@ExtendWith(MockitoExtension::class)
class AuthApiDocumentify : Documentify() {
    @Mock
    lateinit var service: AuthService

    @InjectMocks
    lateinit var api: AuthController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(api), emptyList(), emptyList())
    }

    @Test
    fun authApi() {
        `when`(service.refresh(any())).thenReturn(Token("access_token", TokenType.ACCESS))

        documentation("리프레시 API") {
            information {
                summary("리프레시 API")
                description("리프레시 API 입니다.")
                tag("인증")
            }
            requestSchema("리프레시 요청")
            requestLine(Method.POST, "/api/auth/refresh")
            requestBody {
                field("refresh_token", "리프레시 토큰", "refresh_token")
            }
            responseSchema("리프레시 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("access_token", "액세스 토큰", "access_token")
            }
        }
    }
}