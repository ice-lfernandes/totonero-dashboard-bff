package com.totonero.dashboard.graphql.dto
data class Bet(
    val id: Long = 1L,
    val name: String = "",
    val scoreMinimumEntry: Int = 0,
    val scoreEntry: Int = 0,
    val periodMatch: String = "",
    val minimumDashMinute: Int = 0,
    val maximumDashMinute: Int = 0,
    val maximumAdvantageInResult: Int = 0,
    val templateMessageTelegram: String = "",
    val automaticSendTelegram: Boolean = false
)