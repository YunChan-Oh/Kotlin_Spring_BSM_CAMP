package com.aicamp.kopring.shorturl.presentation

import com.aicamp.kopring.shorturl.domain.UrlEntity
import com.aicamp.kopring.shorturl.presentation.dto.CreateShortenUrlRequest
import com.aicamp.kopring.shorturl.presentation.dto.CreateShortenUrlResponse
import com.aicamp.kopring.shorturl.service.UrlService
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class ShortenUrlController(
    private val urlService: UrlService,
) {
    private val logger = LoggerFactory.getLogger(ShortenUrlController::class.java)

    @PostMapping("/short-urls")
    fun create(@RequestBody urlRequest: CreateShortenUrlRequest): CreateShortenUrlResponse {
        logger.info("Creating short URL for: ${urlRequest.originalUrl}")
        return urlService.create(urlRequest.toEntity())
    }

    @GetMapping("/short-urls")
    fun findAll(): List<UrlEntity> {
        logger.info("Fetching all URLs")
        return urlService.findAll()
    }

    @GetMapping("/short-urls/{encoded}")
    fun findByShortenUrl(@PathVariable encoded: String, response: HttpServletResponse) {
        logger.info("Redirecting short URL: $encoded")
        try {
            val originalUrl = urlService.findByShortenUrl(encoded)
            response.sendRedirect(originalUrl)
        } catch (e: NoSuchElementException) {
            logger.error("Short URL not found: $encoded", e)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found", e)
        }
    }

    @GetMapping("/short-url")
    fun findByTime(@RequestParam(required = false, value = "createdAfter") time: LocalDateTime?): List<UrlEntity> {
        return urlService.findByTime(time)
    }
}