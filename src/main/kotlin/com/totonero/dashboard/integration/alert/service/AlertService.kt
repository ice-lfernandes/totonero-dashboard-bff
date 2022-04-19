package com.totonero.dashboard.integration.alert.service

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.integration.alert.client.AlertClient
import com.totonero.dashboard.integration.alert.dto.DashboardDTO
import com.totonero.dashboard.integration.alert.dto.TeamProfileDTO
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class AlertService(val alertClient: AlertClient) {

    private val log: Logger = LoggerFactory.getLogger(AlertService::class.qualifiedName)

    fun findMatchesAlive(): List<DashboardDTO> =
        try {
            alertClient.getMatches().let { response ->
                if (response.statusCode == HttpStatus.OK) {
                    return response.body!!
                }
                log.error("stage=error-alert-service-find-matches, msg=matches not found, status=${response.statusCode}")
                throw IntegrationException("matches-not-found")
            }
        } catch (exception: FeignException) {
            log.error(
                "stage=error-alert-service-find-matches, msg=${exception.message}, status=${exception.status()}",
                exception
            )
            throw IntegrationException(exception.message!!)
        }

    fun findTeamProfileByTeamId(teamId: String): TeamProfileDTO =
        try {
            alertClient.findTeamProfileByTeamId(teamId).let { response ->
                if (response.statusCode == HttpStatus.OK) {
                    return response.body!!
                }
                log.error("stage=error-alert-service-find-team, msg=matches not found, status=${response.statusCode}")
                throw IntegrationException("matches-not-found")
            }
        } catch (exception: FeignException) {
            log.error(
                "stage=error-alert-service-find-team, msg=${exception.message}, status=${exception.status()}",
                exception
            )
            throw IntegrationException(exception.message!!)
        }


}