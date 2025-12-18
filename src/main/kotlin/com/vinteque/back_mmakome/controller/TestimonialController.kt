package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import com.vinteque.back_mmakome.model.Testimonial
import com.vinteque.back_mmakome.service.TestimonialService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/testimonial")
class TestimonialController(
    val testimonialService: TestimonialService
) {

    @GetMapping("/all")
    fun getTestimonials(): ResponseEntity<ApiResponseDto<List<Testimonial>>> {
        val testimonials = testimonialService.getAllTestimonials()
        return ApiResponse.success(testimonials)
    }

    @PostMapping("/create")
    fun createTestimonial(@RequestBody @Valid testimonial: Testimonial): ResponseEntity<ApiResponseDto<Testimonial>> {
        val testimonial= testimonialService.createTestimonial(testimonial)
        return ApiResponse.success(testimonial)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTestimonial(@PathVariable id: Long): ResponseEntity<ApiResponseDto<Map<String, String>>> {
        testimonialService.deleteTestimonial(id)
        return ApiResponse.success(mapOf("message" to "Testimonial deleted Successfully"))
    }
}