package com.totonero.dashboard.graphql.dto

data class Match(
    val matchId: String = "",
    val leagueName: String = "",
    val minutesOfMatch: String = "",
    val home: Team = Team(),
    val away: Team = Team(),
    val score: Int = 0
)