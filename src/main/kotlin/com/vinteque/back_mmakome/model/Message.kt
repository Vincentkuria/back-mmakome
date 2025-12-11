package com.vinteque.back_mmakome.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null,
    val name: String,
    val email: String,
    val phone: String,
    val subject: String,
    val message: String,
    @Column(insertable = false, updatable = true)
    var isRead: Boolean?=false,
    @Column(updatable = false, insertable = false)
    val createdAt: Timestamp?=null,
)
