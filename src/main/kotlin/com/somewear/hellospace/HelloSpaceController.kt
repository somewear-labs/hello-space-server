package com.somewear.hellospace

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/somewear")
class HelloSpaceController {

    private val serializer = Json { ignoreUnknownKeys = true }

    @PostMapping
    fun receiveWebhookEvent(@RequestBody json: String) {
        val request = serializer.decodeFromString<WebhookRequest>(json)

        for (payload in request.payloads) {
            println("Received payload from identity. name=${payload.identity.name} identityId=${payload.identity.id} workspace=${payload.workspace?.name} workspaceId=${payload.workspace?.id}")

            for (event in payload.events) {
                when (event) {
                    is WebhookRequest.Event.Location -> {
                        println("Location event received: $event")
                    }
                    is WebhookRequest.Event.Waypoint -> {
                        println("Waypoint event received: $event")
                    }
                    is WebhookRequest.Event.Message -> {
                        println("Message event received: $event")
                    }
                    is WebhookRequest.Event.Data -> {
                        println("Data event received: $event")
                    }
                }
            }
        }
    }
}