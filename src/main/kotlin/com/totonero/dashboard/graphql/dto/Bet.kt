package com.totonero.dashboard.graphql.dto

import com.totonero.dashboard.integration.totonero.dto.enumerator.ResultRequired

data class Bet(
    val id: Long = 1L,
    val name: String = "",
    val enabled: Boolean = false,
    val minimumScore: Int? = null,
    val goodScore: Int? = null,
    val greatScore: Int? = null,
    val periodMatch: String = "",
    val resultRequired: ResultRequired = ResultRequired.FAVORITE_LOSING,
    val equal: Boolean = false,
    val corner: Boolean = false,
    val minimumDashMinute: Int? = null,
    val maximumDashMinute: Int? = null,
    val maximumAdvantageInResult: Int = 0,
    val templateMessageTelegram: String = "",
    val automaticSendTelegram: Boolean = false,
    val rules: List<BetRule> = listOf()
)