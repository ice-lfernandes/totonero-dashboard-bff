package com.totonero.dashboard.integration.alert.dto

data class TeamResponse(
    val teamDTO: TeamDTO = TeamDTO()
)

data class TeamDTO(
    val logo: String = "",
    val name: String = "",
    val teamId: String = ""
)