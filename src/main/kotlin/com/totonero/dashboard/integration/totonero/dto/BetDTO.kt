package com.totonero.dashboard.integration.totonero.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.totonero.dashboard.integration.totonero.dto.enumerator.Comparator
import com.totonero.dashboard.integration.totonero.dto.enumerator.ResultRequired
import com.totonero.dashboard.integration.totonero.dto.enumerator.RuleType
import com.totonero.dashboard.integration.totonero.dto.enumerator.TeamType

data class BetDTO(
    val id: Long = 1L,
    val parentId: Long? = null,
    val name: String = "",
    val nameSimple: String = "",
    @JsonProperty("isFirstHalf") val firstHalf: Boolean = false,
    @JsonProperty("isEnabled") val enabled: Boolean = false,
    @JsonProperty("isEqual") val equal: Boolean = false,
    @JsonProperty("isCorner") val corner: Boolean = false,
    val customerId: Long = 1L,
    val betProperty: BetPropertyDTO = BetPropertyDTO(),
    val betDash: BetDashDTO? = null,
    val betEntry: BetEntryDTO? = null,
    val betRules: List<BetRuleDTO> = listOf()
)

data class RuleDTO(
    val id: Long = 1L,
    val name: String = "",
    val type: RuleType = RuleType.ALERT,
    val parentId: Long = 1L
)

data class BetPropertyDTO(
    val id: Long = 1L,
    val evictId: Long = 1L,
    val unit: Double = 0.0,
    val minimumOdd: Double = 0.0,
    val resultRequired: ResultRequired = ResultRequired.FAVORITE_LOSING,
    val maximumDifferenceInResult: Int = 0,
    val valueEqualGreen: Int = 0,
    val minimumValueEqual: Int = 0,
    val quantityToGreen: Int = 0,
    val cancelIfFavoriteHasLessPlayer: Boolean = false,
    val negativeScoreEvictIsPush: Int = 0
)

data class BetDashDTO(
    val id: Long = 1L,
    val minimumDashMinute: Int = 0,
    val maximumDashMinute: Int = 0,
    val minimumScore: Int = 0,
    val goodScore: Int = 0,
    val greatScore: Int = 0
)

data class BetRuleDTO(
    val id: Long = 1L,
    val rule: RuleDTO = RuleDTO(),
    val value: Int = 0,
    val minute: Int = 0,
    val score: Int = 0,
    @JsonProperty("isMandatory") val mandatory: Boolean = false,
    @JsonProperty("isMandatoryAfterRedCard") val mandatoryAfterRedCard: Boolean = false,
    val comparator: Comparator? = null,
    @JsonProperty("team") val teamType: TeamType? = null
)

data class BetEntryDTO(
    val id: Long = 1L,
    val minimumScore: Int = 0,
    val score: Int = 0,
    val minimumGreenMinute: Int = 0,
    val maximumGreenMinute: Int = 0,
    val maximumCheckCloseMinute: Int = 0,
    val maximumCheckCloseNeutralMinute: Int = 0,
    @JsonProperty("isAbleToClose") val ableToClose: Boolean = false
)