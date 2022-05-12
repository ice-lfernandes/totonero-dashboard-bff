package com.totonero.dashboard.integration.bettype.service

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.integration.bettype.client.BetTypeClient
import com.totonero.dashboard.integration.bettype.dto.BetDTO
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BetTypeService(val betTypeClient: BetTypeClient) {

    private val log: Logger = LoggerFactory.getLogger(BetTypeService::class.qualifiedName)

    fun findAllBets(): List<BetDTO> {
        try {
            betTypeClient.findAll().let { response ->
                if (response.statusCode == HttpStatus.OK) {
                    return response.body!!
                }
                log.error("stage=error-bet-type-service, status=${response.statusCode}")
                throw IntegrationException("error-integration")
            }
        } catch (exception: FeignException) {
            log.error(
                "stage=error-bet-type-service, msg=${exception.message}, status=${exception.status()}",
                exception
            )
            throw IntegrationException(exception.message!!)
        }
    }
}