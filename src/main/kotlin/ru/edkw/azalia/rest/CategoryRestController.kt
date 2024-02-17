package ru.edkw.azalia.rest

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.edkw.azalia.domain.Category
import ru.edkw.azalia.domain.Views
import ru.edkw.azalia.dto.BaseResponse
import ru.edkw.azalia.service.CategoryService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/categories")
class CategoryRestController(
    private val categoryService: CategoryService,
) {

    @JsonView(Views.Category::class)
    @GetMapping("{categoryId}")
    fun getCategory(@PathVariable("categoryId") categoryId: String): Category? {
        return categoryService.getCategory(categoryId)
    }

    @GetMapping("/load")
    fun loadCategories(): BaseResponse {
        return BaseResponse(status = categoryService.loadCategories())
    }
}
