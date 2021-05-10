package org.springframework.cloud.gateway.route.builder

import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.BooleanSpec
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder

fun RouteLocatorBuilder.routes(routeLocator: RouteLocatorDsl.() -> Unit): RouteLocator {
    return RouteLocatorDsl(this).apply(routeLocator).build()
}

/**
 * Provider for [RouteLocator] DSL functionality
 */
class RouteLocatorDsl(val builder: RouteLocatorBuilder) {
    private val routes = builder.routes()

    /**
     * DSL to add a route to the [RouteLocator]
     *
     * @see [Route.Builder]
     */
    fun route(id: String? = null, order: Int = 0, uri: String? = null, init: PredicateSpec.() -> Unit) {
        val predicateSpec = if (id == null) {
            RouteLocatorBuilder.RouteSpec(routes).randomId()
        } else {
            RouteLocatorBuilder.RouteSpec(routes).id(id)
        }
        predicateSpec.order(order)
        if (uri != null) {
            predicateSpec.uri(uri)
        }

        predicateSpec.apply(init)

        val route: Route.AsyncBuilder = predicateSpec.routeBuilder
        routes.add(route)
    }

    fun build(): RouteLocator {
        return routes.build()
    }

    /**
     * A helper to return a composed [Predicate] that tests against this [Predicate] AND the [other] predicate
     */
    infix fun BooleanSpec.and(other: BooleanSpec) =
        this.routeBuilder.asyncPredicate(this.predicate.and(other.predicate))

    /**
     * A helper to return a composed [Predicate] that tests against this [Predicate] OR the [other] predicate
     */
    infix fun BooleanSpec.or(other: BooleanSpec) =
        this.routeBuilder.asyncPredicate(this.predicate.or(other.predicate))


}

/**
 * Extension method to add filters {} block to dsl
 */
fun PredicateSpec.filters(init: GatewayFilterSpec.() -> Unit) {
    val spec = createGatewayFilterSpec()
    spec.apply(init)
}