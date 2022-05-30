package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.graphql.dto.Bet
import com.totonero.dashboard.graphql.dto.Rule
import com.totonero.dashboard.integration.totonero.service.TotoneroService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class BetQueryResolver(
    val totoneroService: TotoneroService
) : GraphQLQueryResolver {

    fun bets(token: String): List<Bet> = totoneroService.getAllBets(token).map {
        Bet(
            id = it.id,
            name = it.name,
            automaticSendTelegram = true,
            maximumAdvantageInResult = it.betProperty.maximumDifferenceInResult,
            maximumDashMinute = it.betDash?.maximumDashMinute,
            minimumDashMinute = it.betDash?.minimumDashMinute,
            scoreEntry = it.betEntry.score,
            periodMatch = if (it.firstHalf) "1T" else "2T",
            scoreMinimumEntry = it.betEntry.minimumScore,
            templateMessageTelegram = "Testando...",
            minimumOdd = it.betProperty.minimumOdd,
            unit = it.betProperty.unit,
            rules = it.betRules.map { ruleResponseDTO ->
                Rule(
                    id = ruleResponseDTO.id,
                    type = ruleResponseDTO.rule.type.toString(),
                    value = ruleResponseDTO.value,
                    score = ruleResponseDTO.score,
                    name = ruleResponseDTO.rule.name,
                    mandatory = ruleResponseDTO.mandatory,
                    mandatoryAfterRedCard = ruleResponseDTO.mandatoryAfterRedCard,
                    ruleParentId = ruleResponseDTO.rule.parentId,
                    comparator = ruleResponseDTO.comparator,
                    teamType = ruleResponseDTO.teamType
                )
            }
        )
    }

}