package com.vinteque.back_mmakome.dto

import org.jetbrains.annotations.NotNull
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

data class CaseUpdateDto (
    @field:NotNull
    val id: UUID,
    val image: MultipartFile? = null,
    val title: String,
    val body: String,
    val status: String,
)