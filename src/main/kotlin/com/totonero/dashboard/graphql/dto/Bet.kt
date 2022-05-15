package com.totonero.dashboard.graphql.dto
data class Bet(
    val id: Long = 1L,
    val name: String = "",
    val minimumOdd: Double = 1.0,
    val unit: Double = 0.0,
    val scoreMinimumEntry: Int = 0,
    val scoreEntry: Int = 0,
    val periodMatch: String = "",
    val minimumDashMinute: Int = 0,
    val maximumDashMinute: Int = 0,
    val maximumAdvantageInResult: Int = 0,
    val templateMessageTelegram: String = "",
    val automaticSendTelegram: Boolean = false,
    val rules: List<Rule> = listOf()
)