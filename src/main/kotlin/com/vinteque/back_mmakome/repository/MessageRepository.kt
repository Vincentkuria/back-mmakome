package com.vinteque.back_mmakome.repository

import com.vinteque.back_mmakome.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository: JpaRepository<Message, Long> {
    fun findByIsReadFalse(): List<Message>
}