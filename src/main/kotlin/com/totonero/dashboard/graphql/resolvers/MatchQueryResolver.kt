package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.graphql.dto.Match
import com.totonero.dashboard.graphql.dto.Team
import com.totonero.dashboard.integration.alert.dto.TypeStat
import com.totonero.dashboard.integration.alert.service.AlertService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class MatchQueryResolver(val alertService: AlertService) : GraphQLQueryResolver {

    fun matches(): List<Match> = alertService.findMatchesAlive().map { matchStats ->
        Match(
            matchId = matchStats.matchId,
            score = (Math.random() * 1200).toInt(),
            leagueName = matchStats.match.leagueName,
            minutesOfMatch = matchStats.match.minutesOfMatch.toString(),
            home = Team(
                name = matchStats.match.homeName,
                logo = alertService.findTeamProfile(matchStats.match.homeId).logo,
                score = matchStats.match.homeScore,
                ballPossession = matchStats.stats.find { it.typeStat == TypeStat.BALL_POSSESSION }
                    .let { stats -> stats?.home ?: "0" },
                shotOnGoal = (matchStats.stats.find { it.typeStat == TypeStat.SHOTS_ON_TARGET }
                    .let { stats -> stats?.home ?: "0" }).toInt(),
                shotOffGoal = (matchStats.stats.find { it.typeStat == TypeStat.SHOTS_OFF_TARGET }
                    .let { stats -> stats?.home ?: "0" }).toInt(),
                cornerKick = (matchStats.stats.find { it.typeStat == TypeStat.CORNER }
                    .let { stats -> stats?.home ?: "0" }).toInt(),
                redCard = (matchStats.stats.find { it.typeStat == TypeStat.RED_CARD }
                    .let { stats -> stats?.home ?: "0" }).toInt()
            ),
            away = Team(
                name = matchStats.match.awayName,
                logo = alertService.findTeamProfile(matchStats.match.awayId).logo,
                score = matchStats.match.awayScore,
                ballPossession = matchStats.stats.find { it.typeStat == TypeStat.BALL_POSSESSION }
                    .let { stats -> stats?.away ?: "0" },
                shotOnGoal = (matchStats.stats.find { it.typeStat == TypeStat.SHOTS_ON_TARGET }
                    .let { stats -> stats?.away ?: "0" }).toInt(),
                shotOffGoal = (matchStats.stats.find { it.typeStat == TypeStat.SHOTS_OFF_TARGET }
                    .let { stats -> stats?.away ?: "0" }).toInt(),
                cornerKick = (matchStats.stats.find { it.typeStat == TypeStat.CORNER }
                    .let { stats -> stats?.away ?: "0" }).toInt(),
                redCard = (matchStats.stats.find { it.typeStat == TypeStat.RED_CARD }
                    .let { stats -> stats?.away ?: "0" }).toInt()
            )
        )
    }

}