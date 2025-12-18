package com.vinteque.back_mmakome.service

import com.vinteque.back_mmakome.dto.BlogCreateDto
import com.vinteque.back_mmakome.dto.BlogUpdateDto
import com.vinteque.back_mmakome.model.Blog
import com.vinteque.back_mmakome.repository.BlogRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Service
class BlogService(
    private val blogRepository: BlogRepository,
) {

    fun uploadImage(image : MultipartFile):String {
        val uniqueName = UUID.randomUUID().toString()+"-"+image.originalFilename
        val uploadDir= Paths.get("uploads/")
        if(!Files.exists(uploadDir)) Files.createDirectories(uploadDir)
        val relativePath="uploads/$uniqueName"
        image.transferTo(Paths.get(relativePath))
        return relativePath
    }

    fun createBlog(blogCreateDto: BlogCreateDto): Blog {
        val relativeUrl=uploadImage(blogCreateDto.image)
        return blogRepository.save(Blog(
            image = relativeUrl,
            title = blogCreateDto.title,
            body = blogCreateDto.body,
            status = blogCreateDto.status,
        ))
    }

    fun updateBlog(blogUpdateDto: BlogUpdateDto):Blog {

        val blog=blogRepository.findById(blogUpdateDto.id).get()
        blog.title=blogUpdateDto.title
        blog.body=blogUpdateDto.body
        if (blogUpdateDto.image!=null){
            val relativeUrl=uploadImage(blogUpdateDto.image)
            blog.image=relativeUrl
        }

        return blogRepository.save(blog)
    }

    fun deleteBlog(id: UUID) {
        val blog:Blog?= blogRepository.findById(id).orElse(null)
        blog?.let{
            deleteImage(it.image)
            blogRepository.deleteById(id)
        }
    }

    fun deleteImage(relativePath: String): Boolean {
        try {
            val path = Paths.get(relativePath)
            return Files.deleteIfExists(path)
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    fun getAllBlogs():List<Blog>{
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC,"updatedAt"))
    }

    fun getSingleBlog(id: UUID): Blog? {
        return blogRepository.findById(id).orElse(null)
    }

    fun getPublishedBlogs():List<Blog>{
        return blogRepository.findByStatus("PUBLISHED", Sort.by(Sort.Direction.DESC,"createdAt"))
    }
}