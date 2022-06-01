package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.graphql.dto.Bet
import com.totonero.dashboard.graphql.dto.BetRule
import com.totonero.dashboard.graphql.dto.Rule
import com.totonero.dashboard.integration.totonero.service.TotoneroService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component
import java.util.Objects.isNull

@Component
class BetQueryResolver(
    val totoneroService: TotoneroService
) : GraphQLQueryResolver {

    fun bets(token: String): List<Bet> = totoneroService.getAllBets(token)
        .filter { isNull(it.parentId) }
        .map {
            Bet(
                id = it.id,
                name = it.name,
                automaticSendTelegram = true,
                enabled = it.enabled,
                corner = it.corner,
                equal = it.equal,
                maximumAdvantageInResult = it.betProperty.maximumDifferenceInResult,
                maximumDashMinute = it.betDash?.maximumDashMinute,
                minimumDashMinute = it.betDash?.minimumDashMinute,
                minimumScore = it.betDash?.minimumScore,
                resultRequired = it.betProperty.resultRequired,
                goodScore = it.betDash?.goodScore,
                greatScore = it.betDash?.greatScore,
                periodMatch = if (it.firstHalf) "1T" else "2T",
                templateMessageTelegram = "Testando...",
                rules = it.betRules.map { betRule ->
                    BetRule(
                        rule = Rule(
                            id = betRule.rule.id,
                            parentId = betRule.rule.parentId,
                            type = betRule.rule.type,
                            name = betRule.rule.name
                        ),
                        score = betRule.score,
                        value = betRule.value,
                        comparator = betRule.comparator,
                        teamType = betRule.teamType,
                        mandatory = betRule.mandatory,
                        mandatoryAfterRedCard = betRule.mandatoryAfterRedCard
                    )
                }
            )
        }

}