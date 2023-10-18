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
    class Account(
        val id: String,
        val workspaceId: String?
    )

    @Serializable
    class Workspace(
        val id: String,
        val name: String
    )

    @Serializable
    class Payload(
        val identity: Identity,
        val account: Account,
        val workspace: Workspace?,
        val events: List<Event>
    )

    @Serializable
    sealed class Event {

        @Serializable
        @SerialName("Location")
        data class Location(
            val latitude: String,
            val longitude: String,
            val timestamp: String,
            val altitude: Float? = null,
            val speedOverGround: Float? = null,
            val courseOverGround: Float? = null
        ) : Event() {

            val timestampInstant: Instant
                get() = Instant.parse(timestamp)
        }

        @Serializable
        @SerialName("HealthActivity")
        data class HealthActivity(
            val timestamp: String,
            var heartRate: Int? = null,
            val coreTemperature: Float? = null,
            val breathingRate: Float? = null,
            val heartRateConfidence: Int? = null,
            val breathingRateConfidence: Int? = null,
            val posture: Int? = null,
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



        @Serializable
        @SerialName("Waypoint")
        data class Waypoint(
            val latitude: String,
            val longitude: String,
            val timestamp: String,
            val name: String,
            val notes: String? = null
        ) : Event() {

            val timestampInstant: Instant
                get() = Instant.parse(timestamp)
        }

        @Serializable
        @SerialName("Message")
        data class Message(
            val content: String,
            val timestamp: String
        ) : Event() {

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
