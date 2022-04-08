package com.totonero.dashboard.integration.analysis.client

import com.totonero.dashboard.integration.analysis.dto.DashScoreResponseDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "analysis-service",
    url = "\${integration.analysis-service.url}"
)
interface AnalysisClient {

    @GetMapping(path = ["/dash/score"])
    fun getScore(
        @RequestParam("betId") betId: Long,
        @RequestParam("rulesId") rulesId: List<Long>,
        @RequestParam("matchId") matchId: Long,
        @RequestParam("favoriteTeamId") favoriteTeamId: Long
    ): ResponseEntity<DashScoreResponseDTO>

}