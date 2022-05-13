package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.graphql.dto.Bet
import com.totonero.dashboard.integration.bettype.dto.BetDTO
import com.totonero.dashboard.integration.bettype.service.BetTypeService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class BetQueryResolver(
    val betTypeService: BetTypeService
) : GraphQLQueryResolver {

    private val log: Logger = LoggerFactory.getLogger(BetQueryResolver::class.qualifiedName)

    fun bets(): List<Bet> = betTypeService.findAllBets().map {
        Bet(
            id = it.id,
            name = it.name,
            automaticSendTelegram = true,
            maximumAdvantageInResult = it.maximumAdvantageInResult,
            maximumDashMinute = it.maximumDashMinute,
            minimumDashMinute = it.minimumDashMinute,
            scoreEntry = it.score,
            periodMatch = if (it.isFirstHalf) "1T" else "2T",
            scoreMinimumEntry = it.score,
            templateMessageTelegram = "Testando...",
            minimumOdd = it.minimumOdd,
            unit = it.unit
        )
    }

}