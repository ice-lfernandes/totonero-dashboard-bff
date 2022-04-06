package com.totonero.dashboard.graphql.dto

data class Team(
    val name: String = "",
    val score: Int = 0,
    val ballPossession: String = "",
    val shotOnGoal: Int = 0,
    val shotOffGoal: Int = 0,
    val cornerKick: Int = 0,
    val redCard: Int = 0
)