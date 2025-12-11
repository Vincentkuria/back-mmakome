package com.vinteque.back_mmakome.dto

import com.vinteque.back_mmakome.model.Message
import com.vinteque.back_mmakome.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ApiResponse {

    fun <T> success(data: T,status: HttpStatus= HttpStatus.OK): ResponseEntity<ApiResponseDto<T>> {
        return ResponseEntity(
            ApiResponseDto(
                success = true,
                status = status.value(),
                data = data,
            ),
            status
        )
    }

    fun error(message: Any, status: HttpStatus): ResponseEntity<ApiResponseDto<Any>> {
        return ResponseEntity(
            ApiResponseDto(
                success = false,
                status = status.value(),
                error = message
            ),
            status
        )
    }
}