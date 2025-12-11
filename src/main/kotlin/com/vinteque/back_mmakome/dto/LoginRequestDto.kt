package com.vinteque.back_mmakome.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto (
    @field:NotBlank(message = "email is required")
    val email: String,
    @field:NotBlank(message = "password is required")
    val password: String
)