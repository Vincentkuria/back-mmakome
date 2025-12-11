package com.vinteque.back_mmakome.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authEntryPoint: AuthEntryPoint
) {

    @Bean
    fun filterChain(
        httpSecurity: HttpSecurity,
        jwtFilter: JwtFilter
    ): SecurityFilterChain {
print("------security")
        return httpSecurity.cors { }
            .csrf { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(authEntryPoint) }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/message/create").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config=CorsConfiguration().apply {
            allowedOrigins=listOf("http://localhost:3000/")
            allowedMethods=listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
            allowedHeaders=listOf("*")
        }

        val source=UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", config )

        return source
    }



    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}