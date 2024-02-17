package ru.edkw.azalia.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import ru.edkw.azalia.domain.Product
import ru.edkw.azalia.repo.CategoryRepo
import ru.edkw.azalia.repo.ProductRepo

@Service
class ProductService(
    private val categoryRepo: CategoryRepo,
    private val productRepo: ProductRepo,
    private val httpService: HttpService
) {

    @Cacheable("products")
    fun getProducts(vararg categoryId: String): List<Product> {
        val categories = categoryRepo.findAllById(categoryId.toList())
        val products = productRepo.findAllByCategoryIdIn(categories.map { it.id })
        return if (products.isEmpty() && categories.any { it.categories.isNotEmpty() }) {
            getProducts(*categories.flatMap { it.categories.map { it.id } }.toTypedArray())
        } else {
            products
        }
    }

    @CacheEvict("products")
    fun loadProducts(): Boolean {
        val categoriesIds = categoryRepo.findCategoriesIds()
        categoriesIds.forEach(::saveProducts)
        return true
    }

    private fun saveProducts(categoryId: String) {
        val products = httpService.getProducts(categoryId).data
        productRepo.saveAll(products)
    }
}
