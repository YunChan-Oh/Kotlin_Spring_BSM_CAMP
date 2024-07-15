package com.aicamp.kopring.practice.controller

import com.aicamp.kopring.practice.dto.SaveMyModelRequest
import com.aicamp.kopring.practice.dto.UpdateMyModelRequest
import com.aicamp.kopring.practice.model.MyModel
import com.aicamp.kopring.practice.service.MyModelService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.web.bind.annotation.*

@RestController
class MyController(
    private val myModelService: MyModelService
) {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @PostMapping("/my-model")
    fun saveMyModel(
        @RequestBody
        request: SaveMyModelRequest,
    ): MyModel {
        return myModelService.save(
            MyModel(
                name = request.name
            )
        )
    }

    @GetMapping("/my-models")
    fun listMyModels(): List<MyModel>
        = myModelService.findAll()

    @PatchMapping("/my-model/{id}")
    fun updateMyModel(
        @PathVariable("id") id: Long,
        @RequestBody request: UpdateMyModelRequest
    ): MyModel {
        val myModel: MyModel = myModelService.findById(id) ?: throw ChangeSetPersister.NotFoundException()

        myModel.name = request.name

        return myModelService.save(myModel)
    }
}