package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/token")
class TokenTestController {

    @GetMapping("/test")
    fun test(): ResponseEntity<ApiResponseDto<Map<String, String>>> {
        return ApiResponse.success(mapOf("message" to "Test was successful"))
    }
}