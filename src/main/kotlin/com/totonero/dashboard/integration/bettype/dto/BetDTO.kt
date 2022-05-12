package com.totonero.dashboard.integration.bettype.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BetDTO(
    val id: Long = 1L,
    val parentId: Long = 1L,
    val evictId: Long = 1L,
    val name: String = "",
    val type: String = "",
    val valueGreen: Int = 0,
    val score: Int = 0,
    val unit: Double = 0.0,
    val minimumScore: Int = 0,
    val minimumUnit: Double = 0.0,
    val minimumAlertMinute: Int = 0,
    val maximumAlertMinute: Int = 0,
    val maximumEntryMinute: Int = 0,
    val minimumGreenMinute: Int = 0,
    val maximumGreenMinute: Int = 0,
    val quantityToGreen: Int = 0,
    val minimumOdd: Double = 0.0,
    val isEqual: Boolean = false,
    val isFirstHalf: Boolean = false,
    val isAbleToClose: Boolean = false,
    val maximumCheckCloseMinute: Int = 0,
    val maximumCheckCloseNeutralMinute: Int = 0,
    val isPush: Boolean = false,
    val isLosingRequired: Boolean = false,
    val maximumAdvantageInResult: Int = 0,
    val nameBalance: String = "",
    val negativeScoreEvictIsPus: Int = 0,
    val isPlus: Boolean = false,
    val isEnabled: Boolean = false,
    val automationLine: Double = 0.0,
    val minuteCornerToCancelEntry: Int = 0,
    val minimumDashMinute: Int = 0,
    val maximumDashMinute: Int = 0
)