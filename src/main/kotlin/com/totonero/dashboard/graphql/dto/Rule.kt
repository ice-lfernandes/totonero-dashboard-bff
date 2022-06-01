package com.totonero.dashboard.graphql.dto

import com.totonero.dashboard.integration.totonero.dto.enumerator.RuleType

data class Rule(
    val id: Long = 0L,
    val parentId: Long = 0L,
    val type: RuleType = RuleType.ALERT,
    val name: String = ""
)