package com.totonero.dashboard.integration.totonero.service

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.integration.totonero.client.TotoneroClient
import com.totonero.dashboard.integration.totonero.dto.DashboardDTO
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class TotoneroService(val totoneroClient: TotoneroClient) {

    private val log: Logger = LoggerFactory.getLogger(TotoneroService::class.qualifiedName)
    private val tokenAuth = "Bearer"


    fun getMatches(token: String): List<DashboardDTO> =
        try {
            totoneroClient.getMatches("$tokenAuth $token").let { response ->
                if (response.statusCode == HttpStatus.OK) {
                    return response.body!!
                }
                log.error("stage=error-totonero-service, status=${response.statusCode}")
                throw IntegrationException("error-integration")
            }
        } catch (exception: FeignException) {
            log.error(
                "stage=error-totonero-service, msg=${exception.message}, status=${exception.status()}",
                exception
            )
            throw IntegrationException(exception.message!!)
        }

}