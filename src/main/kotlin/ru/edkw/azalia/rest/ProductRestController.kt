package ru.edkw.azalia.rest

import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.edkw.azalia.domain.Product
import ru.edkw.azalia.service.ProductService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/products")
class ProductRestController(
    private val productService: ProductService,
) {

    @GetMapping
    fun getProducts(@Param("categoryId") categoryId: String): List<Product> {
        return productService.getProducts(categoryId)
    }

    @GetMapping("/load")
    fun loadProducts(): Boolean {
        return productService.loadProducts()
    }
}
