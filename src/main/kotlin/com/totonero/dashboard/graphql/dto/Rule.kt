package com.totonero.dashboard.graphql.dto

import com.totonero.dashboard.integration.totonero.dto.enumerator.Comparator
import com.totonero.dashboard.integration.totonero.dto.enumerator.TeamType

data class Rule(
    val id: Long = 0L,
    val type: String = "",
    val name: String = "",
    val value: Int = 0,
    val score: Int = 0,
    val ruleParentId: Long = 0L,
    val mandatory: Boolean = false,
    val mandatoryAfterRedCard: Boolean = false,
    val comparator: Comparator? = null,
    val teamType: TeamType? = null
    )