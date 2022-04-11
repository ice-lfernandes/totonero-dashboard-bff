package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.graphql.dto.Match
import com.totonero.dashboard.graphql.dto.Team
import com.totonero.dashboard.integration.alert.dto.TypeStat
import com.totonero.dashboard.integration.alert.service.AlertService
import com.totonero.dashboard.integration.analysis.service.AnalysisService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MatchQueryResolver(
    val alertService: AlertService,
    val analysisService: AnalysisService
) : GraphQLQueryResolver {

    private val log: Logger = LoggerFactory.getLogger(GraphQLQueryResolver::class.qualifiedName)

    fun matches(): List<Match> {
        log.info("stage=checking-matches-alive")
        val listMatches = mutableListOf<Match>()
        alertService.findMatchesAlive().parallelStream().forEach { dashboardDTO ->
            try {
                listMatches.add(
                    Match(
                        matchId = dashboardDTO.matchId.toString(),
                        betName = dashboardDTO.betName,
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
                            logo = getUrlLogoImage(dashboardDTO.homeName, dashboardDTO.homeId),
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
                            logo = getUrlLogoImage(dashboardDTO.awayName, dashboardDTO.awayId),
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
                )
            } catch (exception: Exception) {
                log.warn("stage=error-get-match, match=$dashboardDTO")
            }
        }
        log.info("stage=returning-matches-alive")
        return listMatches
    }

    private fun getUrlLogoImage(name: String, id: String) =
        try {
            alertService.findTeamProfileName(name, id).urlImageLogo
        } catch (exception: IntegrationException) {
            log.error("stage=error-get-logo, name=$name, id=$id", exception)
            ""
        }

}