package net.likelion.dailytales.documentation.authentication

import io.github.bgmsound.documentify.core.Documentify
import net.likelion.dailytales.authentication.api.controller.KakaoOAuthController
import net.likelion.dailytales.authentication.application.service.OAuthService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.restdocs.RestDocumentationContextProvider
import io.restassured.http.Method
import net.likelion.dailytales.authentication.application.OAuthResult
import net.likelion.dailytales.core.domain.authentication.Token
import net.likelion.dailytales.core.domain.authentication.TokenType
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class KakaoOAuthApiDocumentify : Documentify() {
    @Mock
    lateinit var service: OAuthService

    @InjectMocks
    lateinit var api: KakaoOAuthController

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(api), emptyList(), emptyList())
    }

    @Test
    fun kakaoOAuthApi() {
        `when`(service.authenticate(any())).thenReturn(stub())

        documentation("카카오 로그인 API") {
            information {
                summary("카카오 OAuth 인증 API")
                description("카카오 OAuth 인증 API 입니다.")
                tag("인증")
                requestSchema("로그인 요청")
                responseSchema("로그인 응답")
            }
            requestLine(Method.POST, "/api/oauth/kakao")
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