package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.integration.alert.dto.MatchStat
import com.totonero.dashboard.integration.alert.service.AlertService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class MatchQueryResolver(val alertService: AlertService) : GraphQLQueryResolver {

    fun matches(): List<MatchStat> = alertService.findMatchesAlive()

}