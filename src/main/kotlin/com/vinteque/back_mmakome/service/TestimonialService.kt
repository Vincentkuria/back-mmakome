package com.vinteque.back_mmakome.service

import com.vinteque.back_mmakome.model.Testimonial
import com.vinteque.back_mmakome.repository.TestimonialRepository
import org.springframework.stereotype.Service

@Service
class TestimonialService(
    private val testimonialRepository: TestimonialRepository,
) {

    fun getAllTestimonials(): List<Testimonial> {
        return testimonialRepository.findAll()
    }

    fun createTestimonial(testimonial: Testimonial): Testimonial {
        return testimonialRepository.save(testimonial)
    }

    fun deleteTestimonial(id: Long) {
        testimonialRepository.deleteById(id)
    }
}