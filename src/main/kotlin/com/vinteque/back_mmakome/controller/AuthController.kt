package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.config.security.JwtUtil
import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import com.vinteque.back_mmakome.dto.LoginRequestDto
import com.vinteque.back_mmakome.dto.RegisterRequestDto
import com.vinteque.back_mmakome.dto.toUserDto
import com.vinteque.back_mmakome.model.User
import com.vinteque.back_mmakome.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequestDto: LoginRequestDto): ResponseEntity<ApiResponseDto<Any>> {
        val user=userService.findUserByEmail(loginRequestDto.email)

        user?.let {
            if (passwordEncoder.matches(loginRequestDto.password,it.password)){
                val token: String = jwtUtil.generateToken(it.email)!!
                return ApiResponse.success(mapOf("token" to token))
            }
        }

        return ApiResponse.error("wrong email or password", HttpStatus.BAD_REQUEST)



    }

    @PostMapping("/register")
    fun register(@RequestBody @Valid registerRequestDto: RegisterRequestDto): ResponseEntity<ApiResponseDto<Any>> {
        val user=userService.createUser(registerRequestDto.toUserDto())
        return ApiResponse.success(user)
    }
}