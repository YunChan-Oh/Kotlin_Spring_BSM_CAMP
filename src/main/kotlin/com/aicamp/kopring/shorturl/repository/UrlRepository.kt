package com.aicamp.kopring.shorturl.repository

import com.aicamp.kopring.shorturl.domain.UrlEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface UrlRepository : JpaRepository<UrlEntity, Long> {
    fun findByEncodedUrl(encodedUrl: String): UrlEntity?

    @Query("SELECT u FROM UrlEntity u WHERE u.createdAt >= :time")
    fun findAllByCreatedAfter(@Param("time") time: LocalDateTime): List<UrlEntity>
}
