package com.vinteque.back_mmakome.repository

import com.vinteque.back_mmakome.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}