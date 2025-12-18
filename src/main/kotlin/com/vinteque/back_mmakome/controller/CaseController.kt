package com.vinteque.back_mmakome.controller

import com.vinteque.back_mmakome.dto.ApiResponse
import com.vinteque.back_mmakome.dto.ApiResponseDto
import com.vinteque.back_mmakome.dto.CaseCreateDto
import com.vinteque.back_mmakome.dto.CaseUpdateDto
import com.vinteque.back_mmakome.model.Case
import com.vinteque.back_mmakome.service.CaseService
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
@RequestMapping("/case")
class CaseController(
    private val caseService: CaseService
) {

    @PostMapping("/create")
    fun create(@ModelAttribute @Valid caseCreateDto: CaseCreateDto): ResponseEntity<ApiResponseDto<Any>> {
        if (caseCreateDto.image.isEmpty){
            return ApiResponse.error("image field is required", HttpStatus.BAD_REQUEST)
        }

        return ApiResponse.success(caseService.createCase(caseCreateDto))
    }

    @PostMapping("/update")
    fun update(@ModelAttribute @Valid caseUpdateDto: CaseUpdateDto): ResponseEntity<ApiResponseDto<Any>> {
        return ApiResponse.success(caseService.updateCase(caseUpdateDto))
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<ApiResponseDto<Any>> {
        caseService.deleteCase(id)
        return ApiResponse.success(mapOf("message" to "item deleted successfully"))
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<ApiResponseDto<List<Case>>> {
        val cases=caseService.getAllCases()
        return ApiResponse.success(cases)
    }

    @GetMapping("/single/{id}")
    fun getSingleCase(@PathVariable id: UUID): ResponseEntity<ApiResponseDto<Any>> {
        val case=caseService.getSingleCase(id)
        case?.let {
            return ApiResponse.success(it)
        }
        return ApiResponse.error(mapOf("message" to "not found"), HttpStatus.NOT_FOUND)
    }

    @GetMapping("/published")
    fun getPublishedCases(): ResponseEntity<ApiResponseDto<List<Case>>> {
        val cases=caseService.getPublishedCases()
        return ApiResponse.success(cases)
    }
}