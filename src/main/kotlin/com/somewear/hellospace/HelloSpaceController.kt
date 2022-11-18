package com.somewear.hellospace

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/somewear")
class HelloSpaceController {

    private val serializer = Json { ignoreUnknownKeys = true }

    @PostMapping
    fun receiveWebhookEvent(@RequestBody json: String) {
        val request = serializer.decodeFromString<WebhookRequest>(json)

        for (payload in request.payloads) {
            val identity = payload.identity
            println("Received payload for identity. name=${identity.name} id=${identity.id}")

            for (event in payload.events) {
                when (event) {
                    is WebhookRequest.Event.Location -> {
                        println("Location event received: $event")
                    }
                    is WebhookRequest.Event.Data -> {
                        println("Data event received: $event")
                    }
                }
            }
        }
    }
}