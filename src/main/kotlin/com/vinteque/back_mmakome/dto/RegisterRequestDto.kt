package com.vinteque.back_mmakome.dto

import com.vinteque.back_mmakome.model.User
import jakarta.validation.constraints.NotBlank

data class RegisterRequestDto (
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val password: String
)

fun RegisterRequestDto.toUserDto(): User{
    return User(
        name = this.name,
        email = this.email,
        password = this.password,
    )
}