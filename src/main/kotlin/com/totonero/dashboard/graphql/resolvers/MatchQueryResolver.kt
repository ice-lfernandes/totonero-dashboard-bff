package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.exception.IntegrationException
import com.totonero.dashboard.graphql.dto.Match
import com.totonero.dashboard.graphql.dto.Team
import com.totonero.dashboard.integration.alert.service.AlertService
import com.totonero.dashboard.integration.totonero.dto.enumerator.TypeStat
import com.totonero.dashboard.integration.totonero.service.TotoneroService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MatchQueryResolver(
    val alertService: AlertService,
    val totoneroService: TotoneroService
) : GraphQLQueryResolver {

    private val log: Logger = LoggerFactory.getLogger(GraphQLQueryResolver::class.qualifiedName)

    fun matches(token: String): List<Match> {
        log.info("stage=checking-matches-alive")
        val listMatches = mutableListOf<Match>()
        totoneroService.getMatches(token).parallelStream().forEach { dashboardDTO ->
            try {
                listMatches.add(
                    Match(
                        matchId = dashboardDTO.matchDTO.matchId.toString(),
                        betName = dashboardDTO.betDTO.name,
                        score = dashboardDTO.scoreDTO.score,
                        leagueName = dashboardDTO.matchDTO.leagueName,
                        minutesOfMatch = dashboardDTO.matchDTO.minutesOfMatch.toString(),
                        home = Team(
                            id = dashboardDTO.matchDTO.homeId.toString(),
                            name = dashboardDTO.matchDTO.homeName,
                            logo = getUrlLogoImage(dashboardDTO.matchDTO.homeId.toString()),
                            score = dashboardDTO.matchDTO.homeScore,
                            ballPossession = dashboardDTO.stats.find { it.typeStats == TypeStat.BALL_POSSESSION }
                                .let { stats -> stats?.home ?: "0" },
                            attacks = dashboardDTO.stats.find { it.typeStats == TypeStat.ATTACKS }
                                .let { stats -> stats?.home ?: "0" }.toInt(),
                            dangerousAttack = dashboardDTO.stats.find { it.typeStats == TypeStat.DANGEROUS_ATTACKS }
                                .let { stats -> stats?.home ?: "0" }.toInt(),
                            shotOnGoal = (dashboardDTO.stats.find { it.typeStats == TypeStat.SHOTS_ON_TARGET }
                                .let { stats -> stats?.home ?: "0" }).toInt(),
                            shotOffGoal = (dashboardDTO.stats.find { it.typeStats == TypeStat.SHOTS_OFF_TARGET }
                                .let { stats -> stats?.home ?: "0" }).toInt(),
                            cornerKick = (dashboardDTO.stats.find { it.typeStats == TypeStat.CORNER }
                                .let { stats -> stats?.home ?: "0" }).toInt(),
                            redCard = (dashboardDTO.stats.find { it.typeStats == TypeStat.RED_CARD }
                                .let { stats -> stats?.home ?: "0" }).toInt()
                        ),
                        away = Team(
                            id = dashboardDTO.matchDTO.awayId.toString(),
                            name = dashboardDTO.matchDTO.awayName,
                            logo = getUrlLogoImage(dashboardDTO.matchDTO.awayId.toString()),
                            score = dashboardDTO.matchDTO.awayScore,
                            ballPossession = dashboardDTO.stats.find { it.typeStats == TypeStat.BALL_POSSESSION }
                                .let { stats -> stats?.away ?: "0" },
                            attacks = (dashboardDTO.stats.find { it.typeStats == TypeStat.ATTACKS }
                                .let { stats -> stats?.away ?: "0" }).toInt(),
                            dangerousAttack = (dashboardDTO.stats.find { it.typeStats == TypeStat.DANGEROUS_ATTACKS }
                                .let { stats -> stats?.away ?: "0" }).toInt(),
                            shotOnGoal = (dashboardDTO.stats.find { it.typeStats == TypeStat.SHOTS_ON_TARGET }
                                .let { stats -> stats?.away ?: "0" }).toInt(),
                            shotOffGoal = (dashboardDTO.stats.find { it.typeStats == TypeStat.SHOTS_OFF_TARGET }
                                .let { stats -> stats?.away ?: "0" }).toInt(),
                            cornerKick = (dashboardDTO.stats.find { it.typeStats == TypeStat.CORNER }
                                .let { stats -> stats?.away ?: "0" }).toInt(),
                            redCard = (dashboardDTO.stats.find { it.typeStats == TypeStat.RED_CARD }
                                .let { stats -> stats?.away ?: "0" }).toInt()
                        )
                    )
                )
            } catch (exception: Throwable) {
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