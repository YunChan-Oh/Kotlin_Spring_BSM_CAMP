package com.aicamp.kopring.mqtt.service

import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import java.time.LocalDateTime

class MqttListener : MessageHandler {
    override fun handleMessage(message: Message<*>) {
        println("[${LocalDateTime.now()}] $message")
    }
}