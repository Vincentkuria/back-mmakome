package com.vinteque.back_mmakome.service

import com.vinteque.back_mmakome.model.User
import com.vinteque.back_mmakome.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findUserByEmail(email: String): User?=userRepository.findByEmail(email)

    fun createUser(user: User): User {
        return userRepository.save(user)
    }
}