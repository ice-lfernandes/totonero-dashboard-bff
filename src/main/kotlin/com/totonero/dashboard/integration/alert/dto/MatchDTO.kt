package com.totonero.dashboard.integration.alert.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MatchResponseDTO(
    @JsonProperty("data")
    val matchs: List<Match> = listOf(),
    val message: String = ""
)

data class Match(
    val matchId: String = "",
    val leagueName: String = "",
    var minutesOfMatch: Int = 0,
    val homeId: String = "",
    val awayId: String = "",
    val homeName: String = "",
    val awayName: String = "",
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val homeRed: Int = 0,
    val awayRed: Int = 0,
    val homeCorner: Int = 0,
    val awayCorner: Int = 0,
)