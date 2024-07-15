package com.aicamp.kopring.practice.service

import com.aicamp.kopring.practice.model.MyModel
import org.springframework.data.jpa.repository.JpaRepository

interface MyModelRepository : JpaRepository<MyModel,Long>