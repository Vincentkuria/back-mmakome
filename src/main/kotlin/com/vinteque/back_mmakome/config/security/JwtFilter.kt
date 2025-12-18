package com.vinteque.back_mmakome.config.security

import com.vinteque.back_mmakome.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil,
    private val userService: UserService
): OncePerRequestFilter() {

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        print("----- should not filter")
        val excludedPaths = listOf("/auth/**", "/testimonial/all","/message/create", "/blog/single/**", "/case/single/**","/blog/published", "/case/published","/uploads/**")
        val pathMatcher= AntPathMatcher()
        val path = request.servletPath

        return excludedPaths.any { pattern ->
            pathMatcher.match(pattern,path)
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        print("-----do filter")
        val token: String? = request.getHeader("Authorization")?.let {
            it.substring(7)
        }

        if (token==null){
            filterChain.doFilter(request,response)
            return
        }

        val claims=jwtUtil.getClaims(token)
        val email: String = claims!!.subject
        val permissions= claims["permissions"] as List<*>
        val authorities= permissions.map { SimpleGrantedAuthority(it.toString()) }

        val auth= UsernamePasswordAuthenticationToken(email,null,authorities)
        SecurityContextHolder.getContext().authentication=auth
        filterChain.doFilter(request,response)
    }
}