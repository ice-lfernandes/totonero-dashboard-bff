package com.totonero.dashboard.integration.alert.dto

data class DashboardDTO(
    val matchId: Long = 1L,
    val leagueName: String = "",
    val minutesOfMatch: Int = 0,
    val homeName: String = "",
    val awayName: String = "",
    val homeId: String = "",
    val awayId: String = "",
    val homeScore: Int = 0,
    val awayScore: Int = 0,
    val betId: Long = 1L,
    val betName: String = "",
    val rulesId: List<Long> = listOf(),
    val favoriteTeamId: Long = 1L,
    val stats: List<Stats> = listOf(),
)