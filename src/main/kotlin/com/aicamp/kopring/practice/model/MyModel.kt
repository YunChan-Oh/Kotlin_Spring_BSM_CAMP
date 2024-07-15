package com.aicamp.kopring.practice.model

import jakarta.persistence.*

@Entity
@Table(name = "my_model")
data class MyModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String,
)