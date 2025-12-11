package com.vinteque.back_mmakome.dto

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp

data class MessageRequestDto(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val email: String,
    @field:NotBlank
    val phone: String,
    @field:NotBlank
    val subject: String,
    @field:NotBlank
    val message: String,
)
