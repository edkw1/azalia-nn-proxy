package ru.edkw.azalia.rest

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.edkw.azalia.dto.BaseResponse
import ru.edkw.azalia.service.CategoryService
import ru.edkw.azalia.service.ProductService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/sync")
class EntitiesSyncRestController(
    private val categoryService: CategoryService,
    private val productService: ProductService
) {

    @GetMapping("/")
    fun loadData(): BaseResponse {
        categoryService.loadCategories()
        productService.loadProducts()
        return BaseResponse("ok")
    }
}