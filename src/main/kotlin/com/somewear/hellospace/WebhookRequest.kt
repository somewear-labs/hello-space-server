package com.somewear.hellospace

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.util.Base64

@Serializable
class WebhookRequest(
    val requestId: String,
    val payloads: List<Payload>
) {
    @Serializable
    data class Identity(
        val id: String,
        val name: String? = null,
        val type: IdentityType,
        val email: String? = null,
        val username: String? = null,
        val externalId: String? = null
    )

    @Serializable
    class Payload(
        val identity: Identity,
        val events: List<Event>
    )

    @Serializable
    sealed class Event {

        @Serializable
        @SerialName("Location")
        data class Location(
            val latitude: Double,
            val longitude: Double,
            val timestamp: String,
            val altitude: Float? = null,
            val speedOverGround: Float? = null,
            val courseOverGround: Float? = null
        ) : Event() {

            val timestampInstant: Instant
                get() = Instant.parse(timestamp)
        }

        @Serializable
        @SerialName("Data")
        data class Data(
            /**
             * Base64 encoded payload
             */
            val payload: String,
            /**
             * ISO 8601 timestamp
             */
            val timestamp: String
        ) : Event() {

            val payloadBytes: ByteArray
                get() = Base64.getDecoder().decode(payload.toByteArray())

            val timestampInstant: Instant
                get() = Instant.parse(timestamp)
        }
    }
}

enum class IdentityType {
    User,
    Device,
    Integration,
    Resource
}