package com.aicamp.kopring.shorturl.presentation.dto

import java.time.ZonedDateTime

data class CreateShortenUrlResponse (
    val id: Long,
    val originalUrl: String,
    val encodedUrl: String,
    val createdAt: String,
)