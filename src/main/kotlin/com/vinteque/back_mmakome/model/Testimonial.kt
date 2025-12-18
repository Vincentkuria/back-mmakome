package com.vinteque.back_mmakome.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "testimonials")
data class Testimonial(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,
    @field:NotNull
    var testimony: String,
    @field:NotNull
    var name: String,
    @field:NotNull
    var role: String,
    @field:NotNull
    var company: String,
)
