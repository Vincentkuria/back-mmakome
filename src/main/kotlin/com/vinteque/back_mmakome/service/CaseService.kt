package com.vinteque.back_mmakome.service

import com.vinteque.back_mmakome.dto.CaseCreateDto
import com.vinteque.back_mmakome.dto.CaseUpdateDto
import com.vinteque.back_mmakome.model.Case
import com.vinteque.back_mmakome.repository.CaseRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Service
class CaseService(
    private val caseRepository: CaseRepository,
) {

    fun uploadImage(image : MultipartFile):String {
        val uniqueName = UUID.randomUUID().toString()+"-"+image.originalFilename
        val uploadDir= Paths.get("uploads/")
        if(!Files.exists(uploadDir)) Files.createDirectories(uploadDir)
        val relativePath="uploads/$uniqueName"
        image.transferTo(Paths.get(relativePath))
        return relativePath
    }

    fun createCase(caseCreateDto: CaseCreateDto): Case {
        val relativeUrl=uploadImage(caseCreateDto.image)
        return caseRepository.save(Case(
            image = relativeUrl,
            title = caseCreateDto.title,
            body = caseCreateDto.body,
            status = caseCreateDto.status,
        ))
    }

    fun updateCase(caseUpdateDto: CaseUpdateDto):Case {

        val case=caseRepository.findById(caseUpdateDto.id).get()
        case.title=caseUpdateDto.title
        case.body=caseUpdateDto.body
        if (caseUpdateDto.image!=null){
            val relativeUrl=uploadImage(caseUpdateDto.image)
            case.image=relativeUrl
        }

        return caseRepository.save(case)
    }

    fun deleteCase(id: UUID) {
        val case:Case?= caseRepository.findById(id).orElse(null)
        case?.let{
            deleteImage(it.image)
            caseRepository.deleteById(id)
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

    fun getAllCases():List<Case>{
        return caseRepository.findAll(Sort.by(Sort.Direction.DESC,"updatedAt"))
    }

    fun getSingleCase(id: UUID): Case? {
        return caseRepository.findById(id).orElse(null)
    }

    fun getPublishedCases():List<Case>{
        return caseRepository.findByStatus("PUBLISHED", Sort.by(Sort.Direction.DESC,"createdAt"))
    }
}