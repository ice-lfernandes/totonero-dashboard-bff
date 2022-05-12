package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.graphql.dto.Match
import com.totonero.dashboard.graphql.dto.Team
import com.totonero.dashboard.integration.alert.dto.TypeStat
import com.totonero.dashboard.integration.alert.service.AlertService
import com.totonero.dashboard.integration.analysis.service.AnalysisService
import com.totonero.dashboard.integration.automation.service.AutomationService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MatchQueryResolver(
    val alertService: AlertService,
    val analysisService: AnalysisService,
    val automationService: AutomationService
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
                        urlBet365 = dashboardDTO.urlMatch,
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
                            logo = getUrlLogoImage(dashboardDTO.homeId),
                            score = dashboardDTO.homeScore,
                            ballPossession = dashboardDTO.stats.find { it.typeStat == TypeStat.BALL_POSSESSION }
                                .let { stats -> stats?.home ?: "0" },
                            attacks = dashboardDTO.stats.find { it.typeStat == TypeStat.ATTACKS }
                                .let { stats -> stats?.home ?: "0" }.toInt(),
                            dangerousAttack = dashboardDTO.stats.find { it.typeStat == TypeStat.DANGEROUS_ATTACKS }
                                .let { stats -> stats?.home ?: "0" }.toInt(),
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
                            logo = getUrlLogoImage(dashboardDTO.awayId),
                            score = dashboardDTO.awayScore,
                            ballPossession = dashboardDTO.stats.find { it.typeStat == TypeStat.BALL_POSSESSION }
                                .let { stats -> stats?.away ?: "0" },
                            attacks = (dashboardDTO.stats.find { it.typeStat == TypeStat.ATTACKS }
                                .let { stats -> stats?.away ?: "0" }).toInt(),
                            dangerousAttack = (dashboardDTO.stats.find { it.typeStat == TypeStat.DANGEROUS_ATTACKS }
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

    private fun getUrlLogoImage(id: String) =
        try {
            alertService.findTeamProfileByTeamId(id).urlImageLogo
        } catch (exception: IntegrationException) {
            log.error("stage=error-get-logo, id=$id", exception)
            "https://api.sofascore.app/api/v1/team/400455/image"
        }

    fun matchesLocal(): List<Match> {
        return listOf(
            Match(
                matchId = "123456789",
                leagueName = "Campeonato Brasileiro",
                score = 750,
                betName = "LIMIT 2T",
                minutesOfMatch = "86",
                urlBet365 = "http://globo.com",
                home = Team(
                    name = "Flamengo",
                    score = 2,
                    logo = "https://api.sofascore.app/api/v1/team/5981/image",
                    shotOnGoal = 7,
                    shotOffGoal = 5,
                    ballPossession = "65",
                    cornerKick = 7,
                    redCard = 0
                ),
                away = Team(
                    name = "Palmeiras",
                    score = 1,
                    logo = "https://api.sofascore.app/api/v1/team/1963/image",
                    shotOnGoal = 7,
                    shotOffGoal = 5,
                    ballPossession = "65",
                    cornerKick = 7,
                    redCard = 1
                )
            ),
            Match(
                matchId = "123456789",
                leagueName = "Campeonato Brasileiro",
                score = 950,
                betName = "LIMIT 2T",
                minutesOfMatch = "86",
                home = Team(
                    name = "Flamengo",
                    score = 2,
                    logo = "https://api.sofascore.app/api/v1/team/5981/image",
                    shotOnGoal = 7,
                    shotOffGoal = 5,
                    ballPossession = "65",
                    cornerKick = 7,
                    redCard = 0
                ),
                away = Team(
                    name = "Palmeiras",
                    score = 1,
                    logo = "https://api.sofascore.app/api/v1/team/1963/image",
                    shotOnGoal = 7,
                    shotOffGoal = 5,
                    ballPossession = "65",
                    cornerKick = 7,
                    redCard = 1
                )
            ),
            Match(
                matchId = "123456789",
                leagueName = "Campeonato Brasileiro",
                score = 1000,
                betName = "LIMIT 2T",
                minutesOfMatch = "86",
                urlBet365 = "http://globo.com",
                home = Team(
                    name = "Flamengo",
                    score = 2,
                    logo = "https://api.sofascore.app/api/v1/team/5981/image",
                    shotOnGoal = 7,
                    shotOffGoal = 5,
                    ballPossession = "65",
                    cornerKick = 7,
                    redCard = 0
                ),
                away = Team(
                    name = "Palmeiras",
                    score = 1,
                    logo = "https://api.sofascore.app/api/v1/team/1963/image",
                    shotOnGoal = 7,
                    shotOffGoal = 5,
                    ballPossession = "65",
                    cornerKick = 7,
                    redCard = 1
                )
            )
        )
    }
}