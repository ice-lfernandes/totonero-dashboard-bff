package com.totonero.dashboard.graphql.resolvers

import com.totonero.dashboard.Client
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class ClientQueryResolver : GraphQLQueryResolver {

    fun client(): Client = Client("Lucas")

}