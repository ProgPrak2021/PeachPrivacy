package com.peachprivacy.tiltservice.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.util.*

@Component
open class SchemaRemoteTSRepository @Autowired constructor(
    loadBalanceAwareWebClient: WebClient.Builder
): SchemaRepository {
    val schemaMicroserviceWebClient = loadBalanceAwareWebClient.baseUrl("lb://templateservice").build()

    private fun webClientResult(requestSpec: WebClient.RequestHeadersSpec<*>) =
        requestSpec.retrieve().bodyToMono<String>().block()

    private fun WebClient.RequestHeadersSpec<*>.retrieveString() = webClientResult(this)

    override fun get(id: UUID) =
        schemaMicroserviceWebClient.get().uri("/api/template/templates/data/$id").retrieveString()


    override fun set(id: UUID, schema: String) {
        schemaMicroserviceWebClient.put().uri("/api/template/templates/data/$id")
    }

    override fun delete(id: UUID) {
        schemaMicroserviceWebClient.delete().uri("/api/template/templates/data/$id")
    }
}