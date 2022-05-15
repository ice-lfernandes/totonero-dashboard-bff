package com.totonero.dashboard.integration.bettype.client

import com.totonero.dashboard.integration.bettype.dto.BetDTO
import com.totonero.dashboard.integration.bettype.dto.RuleResponseDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    value = "bet-type-service",
    url = "\${integration.bet-type-service.url}"
)
interface BetTypeClient {

    @RequestMapping(path = ["/bet"], method = [RequestMethod.GET])
    fun findAll(): ResponseEntity<List<BetDTO>>

    @RequestMapping(path = ["/rule/bet/{betId}"], method = [RequestMethod.GET])
    fun findRulesByBetId(@PathVariable betId: Long): ResponseEntity<List<RuleResponseDTO>>
}