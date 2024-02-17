package ru.edkw.azalia.rest

import com.fasterxml.jackson.annotation.JsonView
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.edkw.azalia.domain.Views
import ru.edkw.azalia.dto.BaseResponse
import ru.edkw.azalia.dto.ProductsData
import ru.edkw.azalia.service.ProductService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/products")
class ProductRestController(
    private val productService: ProductService,
) {

    @JsonView(Views.Product::class)
    @GetMapping
    fun getProducts(@Param("categoryId") categoryId: String): ProductsData {
        return ProductsData(productService.getProducts(categoryId))
    }

    @GetMapping("/load")
    fun loadProducts(): BaseResponse {
        return BaseResponse(status = productService.loadProducts())
    }
}
