package com.totonero.dashboard.integration.totonero.client

import com.totonero.dashboard.integration.totonero.dto.DashboardDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    value = "totonero-service",
    url = "\${integration.totonero-service.url}"
)
interface TotoneroClient {

    @RequestMapping(path = ["/dash"], method = [RequestMethod.GET])
    fun getMatches(@RequestHeader("Authorization") token: String): ResponseEntity<List<DashboardDTO>>

}