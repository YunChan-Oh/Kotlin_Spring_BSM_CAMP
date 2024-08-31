package com.aicamp.kopring.practice.service

import com.aicamp.kopring.practice.model.MyModel
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class MyModelService(
    val myModelRepository: MyModelRepository
) {
    fun save(
        myModel: MyModel
    ): MyModel
        = myModelRepository.save(myModel)

    fun findAll(): List<MyModel>
    = myModelRepository.findAll()

    fun findById(id: Long): MyModel?
    = myModelRepository.findById(id).getOrNull()
}

