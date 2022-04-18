package com.totonero.dashboard.integration.alert.client

import com.totonero.dashboard.integration.alert.dto.DashboardDTO
import com.totonero.dashboard.integration.alert.dto.TeamProfileDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "alert-service",
    url = "\${integration.alert-service.url}"
)
interface AlertClient {

    @RequestMapping(path = ["/dash/matchs"], method = [RequestMethod.GET])
    fun getMatches(): ResponseEntity<List<DashboardDTO>>

    @RequestMapping(path = ["/team-profile"], method = [RequestMethod.GET])
    fun findTeamProfile(@RequestParam teamId: String, @RequestParam name: String) : ResponseEntity<TeamProfileDTO>

    @RequestMapping(path = ["/team"], method = [RequestMethod.GET])
    fun findTeamProfileByTeamId(@RequestParam teamId: String): ResponseEntity<TeamProfileDTO>
}