package com.totonero.dashboard.integration.alert.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MatchResponseDTO(
    @JsonProperty("data")
    val matchs: List<Match> = listOf(),
    val message: String = ""
)

data class Match(
    val leagueName: String = "",
)