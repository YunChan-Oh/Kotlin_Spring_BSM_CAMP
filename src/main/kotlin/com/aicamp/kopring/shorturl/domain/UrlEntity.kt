package com.aicamp.kopring.shorturl.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Entity
@Table(name = "url")
data class UrlEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val originalUrl: String,

    val encodedUrl: String,

    var clickCount: Int = 0,

    @field:CreationTimestamp
    @Column(nullable = false,updatable = false)
    val createdAt: LocalDateTime? = null
)