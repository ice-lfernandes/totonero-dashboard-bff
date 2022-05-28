package com.totonero.dashboard.graphql.dto

data class Match(
    val matchId: String = "",
    val leagueName: String = "",
    val leagueId: Long = 0L,
    val minutesOfMatch: String = "",
    val home: Team = Team(),
    val away: Team = Team(),
    val score: Int = 0,
    val minimumScore: Int = 0,
    val goodScore: Int = 0,
    val greatScore: Int = 0,
    val betName: String = "",
    val urlBet365: String? = null
)