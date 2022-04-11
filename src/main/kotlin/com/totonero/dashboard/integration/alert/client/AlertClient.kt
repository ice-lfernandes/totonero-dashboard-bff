package com.totonero.dashboard.integration.alert.client

import com.totonero.dashboard.integration.alert.dto.DashboardDTO
import com.totonero.dashboard.integration.alert.dto.TeamProfileDTO
import com.totonero.dashboard.integration.alert.dto.TeamProfileResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(
    value = "alert-service",
    url = "\${integration.alert-service.url}"
)
interface AlertClient {

    @RequestMapping(path = ["/dash/matchs"], method = [RequestMethod.GET])
    fun getMatches(): ResponseEntity<List<DashboardDTO>>

    @RequestMapping(path = ["/team-profile"], method = [RequestMethod.GET])
    fun findTeamProfile(@RequestParam teamId: String, @RequestParam name: String) : ResponseEntity<TeamProfileResponse>
}