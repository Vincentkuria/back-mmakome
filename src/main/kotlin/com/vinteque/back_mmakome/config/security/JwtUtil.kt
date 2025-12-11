package com.vinteque.back_mmakome.config.security

import com.vinteque.back_mmakome.service.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtUtil (
    private val env: Environment,
    private val userService: UserService
) {
    fun generateToken(email: String): String? {

        val user=userService.findUserByEmail(email)

        val permissions: List<String> =user!!.userPermission.map { it.permission.name }

        return Jwts.builder()
            .setSubject(user.email)
            .claim("permissions",permissions)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis()+1000*60*60*24))
            .signWith(Keys.hmacShaKeyFor(env.getProperty("jwt.secret")!!.toByteArray()), SignatureAlgorithm.HS512)
            .compact()
    }

    fun getClaims(token:String): Claims? {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(env.getProperty("jwt.secret")!!.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body
    }
}