package com.totonero.dashboard.integration.alert.response

import com.totonero.dashboard.integration.alert.dto.MatchStat

data class MatchResponse(
    val matches: List<MatchStat> = listOf()
)