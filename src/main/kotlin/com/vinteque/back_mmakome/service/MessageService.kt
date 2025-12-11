package com.vinteque.back_mmakome.service

import com.vinteque.back_mmakome.model.Message
import com.vinteque.back_mmakome.repository.MessageRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MessageService(
    private val messageRepository: MessageRepository
) {
    fun create(message: Message): Message= messageRepository.save(message)

    fun getAll(): List<Message> = messageRepository.findAll()

    fun singleMessage(id: Long): Optional<Message?> = messageRepository.findById(id)

    fun unread()=messageRepository.findByIsReadFalse()

    fun delete(id: Long)=messageRepository.deleteById(id)

    fun markAsRead(id: Long){
       val message: Message?= messageRepository.findById(id).orElse(null)
        message?.let {
            it.isRead=true
            messageRepository.save(it)
        }
    }
}