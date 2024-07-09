package net.likelion.dailytales.documentation.authentication

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.api.controller.GoogleOAuthController
import net.likelion.dailytales.authentication.application.OAuthResult
import net.likelion.dailytales.authentication.application.service.OAuthService
import net.likelion.dailytales.core.domain.authentication.Token
import net.likelion.dailytales.core.domain.authentication.TokenType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider

@ExtendWith(MockitoExtension::class)
class GoogleOAuthApiDocumentify : Documentify() {
    @Mock
    lateinit var service: OAuthService

    @InjectMocks
    lateinit var api: GoogleOAuthController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        standAloneSetup(provider, api)
    }

    @Test
    fun googleOAuthApi() {
        `when`(service.authenticate(any())).thenReturn(stub())

        documentation("google-oauth-api") {
            information {
                summary("구글 OAuth 인증 API")
                description("구글 OAuth 인증 API 입니다.")
                tag("authentication")
            }
            requestLine(Method.POST, "/api/oauth/google")
            requestBody {
                field("access_token", "인증 코드", "access_token")
            }

            responseLine(HttpStatus.OK)
            responseBody {
                field("access_token", "액세스 토큰", stub().accessToken.value())
                field("refresh_token", "리프레시 토큰", stub().refreshToken.value())
            }
        }
    }

    private fun stub(): OAuthResult {
        val accessToken = "access_token"
        val refreshToken = "refresh_token"
        return OAuthResult(
            Token(accessToken, TokenType.ACCESS),
            Token(refreshToken, TokenType.REFRESH)
        )
    }
}