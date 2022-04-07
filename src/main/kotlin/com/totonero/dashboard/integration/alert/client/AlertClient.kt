package com.totonero.dashboard.integration.alert.client

import com.totonero.dashboard.integration.alert.dto.TeamResponse
import com.totonero.dashboard.integration.alert.response.MatchResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    value = "alert-service",
    url = "\${integration.alert-service.url}/alert"
)
interface AlertClient {

    @RequestMapping(path = ["/matches"], method = [RequestMethod.GET])
    fun findMatches() : ResponseEntity<MatchResponse>

    @RequestMapping(path = ["/team/{teamId}"], method = [RequestMethod.GET])
    fun findTeam(@PathVariable teamId: String) : ResponseEntity<TeamResponse>

}