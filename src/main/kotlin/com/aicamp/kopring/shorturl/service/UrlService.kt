package com.aicamp.kopring.shorturl.service

import com.aicamp.kopring.shorturl.domain.UrlEntity
import com.aicamp.kopring.shorturl.repository.UrlRepository
import com.aicamp.kopring.shorturl.presentation.dto.CreateShortenUrlResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UrlService(
    private val urlRepository: UrlRepository
) {
    private val base62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    private val baseUrl = "http://localhost:8080/api/short-url/"

    fun create(urlEntity: UrlEntity): CreateShortenUrlResponse {
        val encodedUrl = encodeUrl(urlEntity.originalUrl)
        val urlEntity = urlRepository.save(UrlEntity(originalUrl = urlEntity.originalUrl, encodedUrl = encodedUrl))
        return CreateShortenUrlResponse(
            id = urlEntity.id,
            originalUrl = urlEntity.originalUrl,
            encodedUrl = baseUrl + urlEntity.encodedUrl,
            createdAt = urlEntity.createdAt.toString()
        )
    }

    fun findAll(): List<UrlEntity> {
        return urlRepository.findAll()
    }

    @Transactional
    fun findByShortenUrl(encoded: String): String {
        var result: UrlEntity? = urlRepository.findByEncodedUrl(encoded)
        result?.clickCount = result?.clickCount?.plus(1)!!
        return result?.originalUrl
            ?: throw NoSuchElementException("No URL found for $encoded")
    }

    private fun encodeUrl(url: String): String {
        val uuid = UUID.randomUUID()
        val number = uuid.mostSignificantBits and Long.MAX_VALUE
        return encode(number)
    }

    private fun encode(number: Long): String {
        var n = number
        val sb = StringBuilder()
        while (n > 0) {
            sb.append(base62[(n % 62).toInt()])
            n /= 62
        }
        return sb.reverse().toString().padStart(6, '0')
    }

    fun findByTime(time: LocalDateTime?): List<UrlEntity>{
        if (time==null){
            val t = LocalDateTime.now().minusMinutes(10)
            return urlRepository.findAllByCreatedAfter(t)
        }
        return urlRepository.findAllByCreatedAfter(time)
    }
}