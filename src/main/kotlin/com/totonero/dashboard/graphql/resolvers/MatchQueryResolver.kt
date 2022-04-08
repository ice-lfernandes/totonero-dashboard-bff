package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.graphql.dto.Match
import com.totonero.dashboard.graphql.dto.Team
import com.totonero.dashboard.integration.alert.dto.TypeStat
import com.totonero.dashboard.integration.alert.service.AlertService
import com.totonero.dashboard.integration.analysis.service.AnalysisService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class MatchQueryResolver(
    val alertService: AlertService,
    val analysisService: AnalysisService
) : GraphQLQueryResolver {

    fun matches(): List<Match> = alertService.findMatchesAlive().map { dashboardDTO ->
        Match(
            matchId = dashboardDTO.matchId.toString(),
            score = analysisService.getScore(
                betId = dashboardDTO.betId,
                rulesId = dashboardDTO.rulesId,
                matchId = dashboardDTO.matchId,
                favoriteTeamId = dashboardDTO.favoriteTeamId
            ),
            leagueName = dashboardDTO.leagueName,
            minutesOfMatch = dashboardDTO.minutesOfMatch.toString(),
            home = Team(
                name = dashboardDTO.homeName,
//                logo = alertService.findTeamProfile(matchStats.match.homeId).logo,
                score = dashboardDTO.homeScore,
                ballPossession = dashboardDTO.stats.find { it.typeStat == TypeStat.BALL_POSSESSION }
                    .let { stats -> stats?.home ?: "0" },
                shotOnGoal = (dashboardDTO.stats.find { it.typeStat == TypeStat.SHOTS_ON_TARGET }
                    .let { stats -> stats?.home ?: "0" }).toInt(),
                shotOffGoal = (dashboardDTO.stats.find { it.typeStat == TypeStat.SHOTS_OFF_TARGET }
                    .let { stats -> stats?.home ?: "0" }).toInt(),
                cornerKick = (dashboardDTO.stats.find { it.typeStat == TypeStat.CORNER }
                    .let { stats -> stats?.home ?: "0" }).toInt(),
                redCard = (dashboardDTO.stats.find { it.typeStat == TypeStat.RED_CARD }
                    .let { stats -> stats?.home ?: "0" }).toInt()
            ),
            away = Team(
                name = dashboardDTO.awayName,
//                logo = alertService.findTeamProfile(matchStats.match.awayId).logo,
                score = dashboardDTO.awayScore,
                ballPossession = dashboardDTO.stats.find { it.typeStat == TypeStat.BALL_POSSESSION }
                    .let { stats -> stats?.away ?: "0" },
                shotOnGoal = (dashboardDTO.stats.find { it.typeStat == TypeStat.SHOTS_ON_TARGET }
                    .let { stats -> stats?.away ?: "0" }).toInt(),
                shotOffGoal = (dashboardDTO.stats.find { it.typeStat == TypeStat.SHOTS_OFF_TARGET }
                    .let { stats -> stats?.away ?: "0" }).toInt(),
                cornerKick = (dashboardDTO.stats.find { it.typeStat == TypeStat.CORNER }
                    .let { stats -> stats?.away ?: "0" }).toInt(),
                redCard = (dashboardDTO.stats.find { it.typeStat == TypeStat.RED_CARD }
                    .let { stats -> stats?.away ?: "0" }).toInt()
            )
        )
    }

}