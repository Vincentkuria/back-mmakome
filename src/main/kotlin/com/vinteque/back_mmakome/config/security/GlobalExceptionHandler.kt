package com.vinteque.back_mmakome.config.security

import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler

class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationEx(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponseDto<Any>> {
        val errors=ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage?: "Invalid value")
        }

        return ApiResponse.error(errors,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgEx(ex: IllegalArgumentException): ResponseEntity<ApiResponseDto<Any>> {
        return ApiResponse.error(ex.message?:"Invalid Argument", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(ex: AccessDeniedException)
            = ApiResponse.error(ex.message?:"Forbidden You don't have access", HttpStatus.FORBIDDEN)


    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleBadJson(ex: HttpMessageNotReadableException)
            = ApiResponse.error("Malformed JSON request", HttpStatus.BAD_REQUEST)

    @ExceptionHandler(Exception::class)
    fun handleGenericEx(ex: Exception): ResponseEntity<ApiResponseDto<Any>> {
        return ApiResponse.error(ex.message?:"Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}