package com.totonero.dashboard.integration.totonero.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.totonero.dashboard.integration.totonero.dto.enumerator.AcquireType
import com.totonero.dashboard.integration.totonero.dto.enumerator.Comparator
import com.totonero.dashboard.integration.totonero.dto.enumerator.TeamType
import com.totonero.dashboard.integration.totonero.dto.enumerator.TypeStat

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class DashboardDTO(
    val matchDTO: MatchDTO = MatchDTO(),
    val nameBet: String = "",
    val score: Int = 0,
    val goodScore: Int = 0,
    val greatScore: Int = 0,
    val minimumScore: Int = 0,
    val stats: List<StatsEventDTO> = listOf()
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MatchDTO(
    val matchId: Long = 1L,
    val leagueId: Long = 1L,
    val leagueName: String = "",
    val minutesOfMatch: Int = 0,
    val homeId: Long = 1L,
    val awayId: Long = 1L,
    val homeName: String = "",
    val awayName: String = "",
    val homeScore: Int = 0,
    val awayScore: Int = 0
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class StatsEventDTO(
    @JsonProperty("type") val id: Int = 0,
    val home: String = "",
    val away: String = "",
    val typeStats: TypeStat = TypeStat.ATTACKS
)