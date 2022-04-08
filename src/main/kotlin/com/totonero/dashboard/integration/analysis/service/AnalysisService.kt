package com.totonero.dashboard.integration.analysis.service

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.integration.analysis.client.AnalysisClient
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class AnalysisService(val analysisClient: AnalysisClient) {

    private val log: Logger = LoggerFactory.getLogger(AnalysisService::class.qualifiedName)

    fun getScore(
        betId: Long,
        rulesId: List<Long>,
        matchId: Long,
        favoriteTeamId: Long
    ): Int =
        try {
            analysisClient.getScore(betId, rulesId, matchId, favoriteTeamId).let { response ->
                if (response.statusCode == HttpStatus.OK) {
                    return response.body!!.score
                }
                log.error("stage=error-analysis-service-get-score, msg=score not calculated, status=${response.statusCode}")
                throw IntegrationException("matches-not-found")
            }
        } catch (exception: FeignException) {
            log.error(
                "stage=error-analysis-service-get-score, msg=${exception.message}, status=${exception.status()}",
                exception
            )
            throw IntegrationException(exception.message!!)
        }

}