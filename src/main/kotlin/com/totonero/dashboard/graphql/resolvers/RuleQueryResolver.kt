package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.graphql.dto.Rule
import com.totonero.dashboard.integration.totonero.dto.RuleDTO
import com.totonero.dashboard.integration.totonero.service.TotoneroService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class RuleQueryResolver(
    val totoneroService: TotoneroService
) : GraphQLQueryResolver {

    fun rulesAvailable(): List<Rule> =
        totoneroService.getAllRulesAvailable().map {
            Rule(
                id = it.id,
                parentId = it.parentId,
                type = it.type,
                name = it.name
            )
        }

}