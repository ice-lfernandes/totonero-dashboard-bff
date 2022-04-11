package com.totonero.dashboard.integration.alert.dto

data class TeamProfileResponse(
    val teamProfileDTO: TeamProfileDTO = TeamProfileDTO()
)

data class TeamProfileDTO(
    val name: String = "",
    val urlImageLogo: String = "",
    val teamId: Long? = null
)