package com.vinteque.back_mmakome.dto

import java.sql.Timestamp

data class BlogGetDto(
    val id: String,
    val image: String,
    val title: String,
    val body: String,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
)
