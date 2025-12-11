package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import com.vinteque.back_mmakome.dto.MessageRequestDto
import com.vinteque.back_mmakome.model.Message
import com.vinteque.back_mmakome.service.MessageService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/message")
class MessageController(
    private val messageService: MessageService
) {

    @PostMapping("/create")
    fun create(@RequestBody @Valid messageRequestDto: MessageRequestDto): ResponseEntity<ApiResponseDto<Message>> {
        val newMessage=messageService.create(Message(
            name = messageRequestDto.name,
            email = messageRequestDto.email,
            phone = messageRequestDto.phone,
            subject = messageRequestDto.subject,
            message = messageRequestDto.message,
        ))

        return ApiResponse.success(newMessage)
    }

    @GetMapping("/get-all")
    fun getAllMessage(): ResponseEntity<ApiResponseDto<List<Message>>> {
        val messages=messageService.getAll()
        return ApiResponse.success(messages)
    }

    @GetMapping("/{id}")
    fun getSingleMessage(@PathVariable id: Long): ResponseEntity<ApiResponseDto<Optional<Message?>>> {
        val message=messageService.singleMessage(id)
        return ApiResponse.success(message)
    }
    @GetMapping("/unread")
    fun noOfUnread(): ResponseEntity<ApiResponseDto<Map<String, Int>>> {
        val unReadSize: Int=messageService.unread().size
        return ApiResponse.success(mapOf("unread" to unReadSize))
    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ApiResponseDto<Map<String, String>>> {
        messageService.delete(id)
        return ApiResponse.success(mapOf("message" to "message deleted successfully"))
    }

    @PostMapping("/markRead/{id}")
    fun markRead(@PathVariable id: Long): ResponseEntity<ApiResponseDto<Map<String, String>>> {
        messageService.markAsRead(id)
        return ApiResponse.success(mapOf("message" to "message marked as read"))
    }
}