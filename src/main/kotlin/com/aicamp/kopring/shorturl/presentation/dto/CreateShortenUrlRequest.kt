package com.aicamp.kopring.shorturl.presentation.dto

import com.aicamp.kopring.shorturl.domain.UrlEntity

data class CreateShortenUrlRequest (
    val originalUrl: String,
    val encodedUrl: String
){
    fun toEntity(): UrlEntity{
        return UrlEntity(
            originalUrl = originalUrl,
            encodedUrl = encodedUrl
        )
    }
}