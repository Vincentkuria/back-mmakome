package com.vinteque.back_mmakome.dto

import org.jetbrains.annotations.NotNull
import org.springframework.web.multipart.MultipartFile

data class BlogCreateDto(
    val image: MultipartFile,
    @field:NotNull
    val title: String,
    @field:NotNull
    val body: String,
    @field:NotNull
    val status: String,
)