package com.aicamp.kopring.mqtt.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "mqtts")
data class Mqtt (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val clientId: String,

    val message: String,

    val createdAt: LocalDateTime,
)