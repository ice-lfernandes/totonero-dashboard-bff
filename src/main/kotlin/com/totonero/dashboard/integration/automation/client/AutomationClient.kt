package com.totonero.dashboard.integration.automation.client

import com.totonero.dashboard.integration.automation.dto.MarketDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "automation-service",
    url = "\${integration.automation-service.url}"
)
interface AutomationClient {

    @RequestMapping(path = ["/market/corners-cards"], method = [RequestMethod.GET])
    fun isMarketCornersCardsOpen(
        @RequestParam homeTeam: String,
        @RequestParam awayTeam: String
    ): ResponseEntity<MarketDTO>

}