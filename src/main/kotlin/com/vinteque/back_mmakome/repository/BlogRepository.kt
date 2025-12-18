package com.vinteque.back_mmakome.repository

import com.vinteque.back_mmakome.model.Blog
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BlogRepository: JpaRepository<Blog, UUID> {

    fun findByStatus(status: String,sort: Sort): List<Blog>
}