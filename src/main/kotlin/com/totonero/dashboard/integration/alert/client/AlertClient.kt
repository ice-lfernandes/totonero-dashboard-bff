package com.totonero.dashboard.integration.alert.client

import com.totonero.dashboard.integration.alert.response.MatchResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    value = "alert-service",
    url = "http://localhost:8020/alert"
)
interface AlertClient {

    @RequestMapping(path = ["/matches"], method = [RequestMethod.GET])
    fun findMatches() : ResponseEntity<MatchResponse>

}