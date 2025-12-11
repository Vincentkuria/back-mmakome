package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.dto.BlogCreateDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController {

    @PostMapping("/create")
    fun create(@ModelAttribute @Valid blogCreateDto: BlogCreateDto){
//        TODO:Validate the file
//        TODO:Store the file
//        TODO:store the entity
    }
}