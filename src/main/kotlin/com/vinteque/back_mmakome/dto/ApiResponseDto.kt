package com.vinteque.back_mmakome.dto

import io.jsonwebtoken.lang.Objects
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity

data class ApiResponseDto<T>(
    val success: Boolean,
    val status: Int,
    val data:T?=null,
    val error: Any?=null
)
