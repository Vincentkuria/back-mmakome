package com.vinteque.back_mmakome.repository

import com.vinteque.back_mmakome.model.Testimonial
import org.springframework.data.jpa.repository.JpaRepository

interface TestimonialRepository: JpaRepository<Testimonial, Long> {
}