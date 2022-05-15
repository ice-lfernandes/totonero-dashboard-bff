package com.totonero.dashboard.integration.bettype.dto

data class RuleResponseDTO(
    val id: Long = 0L,
    val type: String = "",
    val name: String = "",
    val value: Int = 0,
    val score: Int = 0,
    val ruleParentId: Long = 0L,
    val isMandatory: Boolean = false,
    val isMandatoryAfterRedCard: Boolean = false,
    val isUnderdogTeam: Boolean = false,
    val isEqual: Boolean = false
)