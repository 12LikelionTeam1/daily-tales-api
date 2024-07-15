package net.likelion.dailytales.documentation.writing

import io.github.bgmsound.documentify.core.Documentify
import io.restassured.http.Method
import net.likelion.dailytales.authentication.infrastructure.security.LoggedInUserArgumentResolver
import net.likelion.dailytales.writing.api.controller.WritingStatisticsController
import net.likelion.dailytales.writing.application.statistics.MainKeywordDto
import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerDayDto
import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerMonthDto
import net.likelion.dailytales.writing.application.statistics.WritingStatisticsService
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
import java.time.Month

@ExtendWith(MockitoExtension::class)
class WritingStatisticsApiDocumentify : Documentify() {
    @Mock
    lateinit var writingStatisticsService: WritingStatisticsService

    @Mock
    lateinit var argumentResolver: LoggedInUserArgumentResolver

    @InjectMocks
    lateinit var writingStatisticsApi: WritingStatisticsController

    @BeforeEach
    fun setup(provider: RestDocumentationContextProvider) {
        setupMock(provider, listOf(writingStatisticsApi), emptyList(), listOf(argumentResolver))
        `when`(argumentResolver.supportsParameter(any())).thenReturn(true)
        `when`(argumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn("userId")
    }

    @Test
    fun getMainKeywordsApi() {
        `when`(writingStatisticsService.getMainKeywords(any(), any()))
            .thenReturn(mainKeywordsStub())

        documentation("메인 키워드 조회 API") {
            information {
                summary("메인 키워드 조회 API")
                description("메인 키워드 조회 API 입니다.")
                tag("글 통계")
            }
            requestSchema("메인 키워드 조회 요청")
            requestLine(Method.GET, "/api/me/writings/statistics/main-keywords") {
                queryParameter("size", "조회할 메인 키워드 개수", "3")
            }
            responseSchema("메인 키워드 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("main_keywords[].keyword", "메인 키워드", "keyword")
                field("main_keywords[].frequency", "키워드 등장 횟수", 1)
            }
        }
    }

    @Test
    fun getTotalWritingsPerMonthApi() {
        `when`(writingStatisticsService.getTotalWritingsPerMonth(any(), any()))
            .thenReturn(totalWritingsPerMonthStub())

        documentation("월별 글 작성 통계 조회 API") {
            information {
                summary("월별 글 작성 통계 조회 API")
                description("월별 글 작성 통계 조회 API 입니다.")
                tag("글 통계")
            }
            requestSchema("월별 글 작성 통계 조회 요청")
            requestLine(Method.GET, "/api/me/writings/statistics/total-writings-per-month") {
                queryParameter("year", "조회할 연도", "2024")
            }
            responseSchema("월별 글 작성 통계 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("total_writings_of_year", "연간 작성한 글의 갯수", "12")
                field("total_writings_per_month.JANUARY", "1월 작성 글 수", 1)
                field("total_writings_per_month.FEBRUARY", "2월 작성 글 수", 1)
                field("total_writings_per_month.MARCH", "3월 작성 글 수", 1)
                field("total_writings_per_month.APRIL", "4월 작성 글 수", 1)
                field("total_writings_per_month.MAY", "5월 작성 글 수", 1)
                field("total_writings_per_month.JUNE", "6월 작성 글 수", 1)
                field("total_writings_per_month.JULY", "7월 작성 글 수", 1)
                field("total_writings_per_month.AUGUST", "8월 작성 글 수", 1)
                field("total_writings_per_month.SEPTEMBER", "9월 작성 글 수", 1)
                field("total_writings_per_month.OCTOBER", "10월 작성 글 수", 1)
                field("total_writings_per_month.NOVEMBER", "11월 작성 글 수", 1)
                field("total_writings_per_month.DECEMBER", "12월 작성 글 수", 1)
            }
        }
    }

    @Test
    fun getTotalWritingsPerDayApi() {
        `when`(writingStatisticsService.getTotalWritingsPerDay(any(), any(), any()))
            .thenReturn(totalWritingsPerDayStub())

        documentation("일별 글 작성 통계 조회 API") {
            information {
                summary("일별 글 작성 통계 조회 API")
                description(
                    """
                        일별 글 작성 통계 조회 API 입니다.
                        1일부터 선택한 월의 마지막 날까지의 글 작성 통계를 조회합니다.
                    """.trimIndent()
                )
                tag("글 통계")
            }
            requestSchema("일별 글 작성 통계 조회 요청")
            requestLine(Method.GET, "/api/me/writings/statistics/total-writings-per-day") {
                queryParameter("year", "조회할 연도", "2024")
                queryParameter("month", "조회할 월", "7")
            }
            responseSchema("일별 글 작성 통계 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field(
                    "total_writings_per_day",
                    "일마다 작성한 글 수",
                    totalWritingsPerDayStub().totalWritingsPerDay
                )
            }
        }
    }

    @Test
    fun countPublishedWritingsApi() {
        documentation("작성한 글 수 조회 API") {
            information {
                summary("작성한 글 수 조회 API")
                description("작성한 글 수 조회 API 입니다.")
                tag("글 통계")
            }
            requestSchema("작성한 글 수 조회 요청")
            requestLine(Method.GET, "/api/me/writings/statistics/published-writings")
            responseSchema("작성한 글 수 조회 응답")
            responseLine(HttpStatus.OK)
            responseBody {
                field("published_writings", "작성한 글 수", 1)
            }
        }
    }

    private fun mainKeywordsStub(): List<MainKeywordDto> {
        return listOf(
            MainKeywordDto(
                "keyword1",
                1
            ),
            MainKeywordDto(
                "keyword2",
                2
            )
        )
    }

    private fun totalWritingsPerMonthStub(): TotalWritingsPerMonthDto {
        return TotalWritingsPerMonthDto(
            12,
            Month.entries.associateWith { 1 }
        )
    }

    private fun totalWritingsPerDayStub(): TotalWritingsPerDayDto {
        return TotalWritingsPerDayDto(
            buildList {
                for (i in 1..31) {
                    add(1)
                }
            }
        )
    }
}