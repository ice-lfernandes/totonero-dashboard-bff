package com.totonero.dashboard.integration.automation.service

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.integration.automation.client.AutomationClient
import com.totonero.dashboard.integration.automation.dto.MarketDTO
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class AutomationService(val automationClient: AutomationClient) {

    private val log: Logger = LoggerFactory.getLogger(AutomationService::class.qualifiedName)

    fun isMarketCornersCardsOpen(homeTeam: String, awayTeam: String): MarketDTO {
        return MarketDTO(isOpen = false, url = "")
//        try {
//            automationClient.isMarketCornersCardsOpen(homeTeam, awayTeam).let { response ->
//                if (response.statusCode == HttpStatus.OK) {
//                    return response.body!!
//                }
//                log.error("stage=error-automation-service-market-corner-open, status=${response.statusCode}")
//                throw IntegrationException("error-integration")
//            }
//        } catch (exception: FeignException) {
//            log.error(
//                "stage=error-automation-service-market-corner-open, msg=${exception.message}, status=${exception.status()}",
//                exception
//            )
//            throw IntegrationException(exception.message!!)
//        }
    }
}