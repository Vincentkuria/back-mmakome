package com.vinteque.back_mmakome.dto

import org.jetbrains.annotations.NotNull
import org.springframework.web.multipart.MultipartFile

data class BlogCreateDto(
    @field:NotNull
    val image: String,
    @field:NotNull
    val title: String,
    val body: MultipartFile,
    @field:NotNull
    val status: String,
)