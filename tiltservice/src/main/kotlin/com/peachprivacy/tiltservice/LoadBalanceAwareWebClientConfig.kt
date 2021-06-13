package com.peachprivacy.tiltservice

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@LoadBalancerClient(name = "cloud-config-discovery-client")
open class LoadBalanceAwareWebClientConfig {
    @LoadBalanced
    @Bean
    fun loadBalanceAwareWebClientBuilder() = WebClient.builder()
}