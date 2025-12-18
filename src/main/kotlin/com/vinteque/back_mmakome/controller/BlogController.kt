package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import com.vinteque.back_mmakome.dto.BlogCreateDto
import com.vinteque.back_mmakome.dto.BlogUpdateDto
import com.vinteque.back_mmakome.model.Blog
import com.vinteque.back_mmakome.service.BlogService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/blog")
class BlogController(
    private val blogService: BlogService
) {

    @PostMapping("/create")
    fun create(@ModelAttribute @Valid blogCreateDto: BlogCreateDto): ResponseEntity<ApiResponseDto<Any>> {
        if (blogCreateDto.image.isEmpty){
            return ApiResponse.error("image field is required", HttpStatus.BAD_REQUEST)
        }

        return ApiResponse.success(blogService.createBlog(blogCreateDto))
    }

    @PostMapping("/update")
    fun update(@ModelAttribute @Valid blogUpdateDto: BlogUpdateDto): ResponseEntity<ApiResponseDto<Any>> {
        return ApiResponse.success(blogService.updateBlog(blogUpdateDto))
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<ApiResponseDto<Any>> {
        blogService.deleteBlog(id)
        return ApiResponse.success(mapOf("message" to "item deleted successfully"))
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<ApiResponseDto<List<Blog>>> {
        val blogs=blogService.getAllBlogs()
        return ApiResponse.success(blogs)
    }

    @GetMapping("/single/{id}")
    fun getSingleBlog(@PathVariable id: UUID): ResponseEntity<ApiResponseDto<Any>> {
        val blog=blogService.getSingleBlog(id)
        blog?.let {
            return ApiResponse.success(it)
        }
        return ApiResponse.error(mapOf("message" to "not found"), HttpStatus.NOT_FOUND)
    }

    @GetMapping("/published")
    fun getPublishedBlogs(): ResponseEntity<ApiResponseDto<List<Blog>>> {
        val blogs=blogService.getPublishedBlogs()
        return ApiResponse.success(blogs)
    }
}