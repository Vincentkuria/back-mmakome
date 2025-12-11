package com.vinteque.back_mmakome.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.time.Instant

@Entity
@Table(name = "blogs")
data class Blog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String?=null,
    val image: String,
    val title: String,
    val body: String,
    val status: String?="DRAFT",
    @Column(insertable = false, updatable = false)
    val createdAt: Timestamp?=null,
    val updatedAt: Timestamp= Timestamp.from(Instant.now())
)
