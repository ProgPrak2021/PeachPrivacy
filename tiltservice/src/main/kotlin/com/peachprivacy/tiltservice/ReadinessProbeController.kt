package com.peachprivacy.tiltservice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// TODO: Readiness probes for ingress aren't relevant once gateway is configured properly
@RestController("/")
class ReadinessProbeController {
    @GetMapping("/")
    fun onReadinessProbeRequest() = ResponseEntity.ok()
}