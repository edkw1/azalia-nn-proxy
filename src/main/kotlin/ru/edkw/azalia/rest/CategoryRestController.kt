package ru.edkw.azalia.rest

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.edkw.azalia.domain.Category
import ru.edkw.azalia.service.CategoryService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/categories")
class CategoryRestController(
    private val categoryService: CategoryService,
) {

    @GetMapping("{categoryId}")
    fun getCategory(@PathVariable("categoryId") categoryId: String): Category? {
        return categoryService.getCategory(categoryId)
    }

    @GetMapping("/load")
    fun loadCategories(): Boolean {
        return categoryService.loadCategories()
    }
}
