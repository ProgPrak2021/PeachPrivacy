package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.body
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.util.*

@Component
open class SchemaRemoteTSRepository @Autowired constructor(
    loadBalanceAwareWebClient: WebClient.Builder
): SchemaRepository {
    val schemaMicroserviceWebClient = loadBalanceAwareWebClient.baseUrl("lb://templateservice").build()

    private fun webClientResult(requestSpec: WebClient.RequestHeadersSpec<*>) =
        requestSpec.retrieve().bodyToMono<String>().onErrorResume(WebClientResponseException::class.java) { exception ->
            if (exception.statusCode.value() == 404) Mono.empty() else Mono.error(exception)    // Handle in Bean filter?
        }.block()

    private fun WebClient.RequestHeadersSpec<*>.retrieveString() = webClientResult(this)

    override fun getValueDefinitions(schema: UUID, dependencies: Map<UUID, Any>): String? {
        return schemaMicroserviceWebClient.post().uri("/api/template/resolve/values/$schema")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(dependencies))
            .retrieveString()
    }

    override fun createValueDefinitions(schema: UUID, values: Map<String, Any?>): String? {
        return schemaMicroserviceWebClient.post().uri("/api/template/templates/data/$schema/values")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(values))
            .retrieveString()
    }

    override fun resolveObject(schema: UUID, dependencies: Map<UUID, Any>): String? {
        return schemaMicroserviceWebClient.post().uri("/api/template/resolve/$schema/object")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(dependencies))
            .retrieveString()
    }

    override fun get(id: UUID) =
        schemaMicroserviceWebClient.get().uri("/api/template/templates/data/$id").retrieveString()


    override fun set(id: UUID, schema: String) {
        schemaMicroserviceWebClient.put().uri("/api/template/templates/data/$id").body(Mono.just(schema)).retrieveString()
    }

    override fun delete(id: UUID) {
        schemaMicroserviceWebClient.delete().uri("/api/template/templates/data/$id").retrieveString()
    }
}