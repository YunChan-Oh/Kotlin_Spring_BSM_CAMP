package com.aicamp.kopring.mqtt.presentation

import com.aicamp.kopring.mqtt.config.MqttConfig
import com.aicamp.kopring.mqtt.config.MqttConfig.Companion.SUB_CLIENT_ID
import com.aicamp.kopring.mqtt.domain.Mqtt
import com.aicamp.kopring.mqtt.presentation.dto.PublishMessageRequest
import com.aicamp.kopring.mqtt.service.MqttService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/mqtt")
class MqttController(
    private val mqttGateway: MqttService.MqttGateway,
    private val mqttService: MqttService
) {
    @GetMapping("/ping")
    fun ping() {
        mqttGateway.sendToMqtt("Hello World!")
    }

    @PostMapping("/publish")
    fun proxyMessage(
        @RequestBody request: PublishMessageRequest
    ): Mqtt{
        val mqtt = Mqtt(
            clientId = SUB_CLIENT_ID,
            message = request.message,
            createdAt = LocalDateTime.now()
        )
        mqttGateway.sendToMqtt("[${SUB_CLIENT_ID}]: ${request.message}")
        return mqttService
    }

}