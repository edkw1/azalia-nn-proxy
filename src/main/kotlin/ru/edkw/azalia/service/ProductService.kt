package ru.edkw.azalia.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import ru.edkw.azalia.domain.Product
import ru.edkw.azalia.repo.CategoryRepo
import ru.edkw.azalia.repo.ProductRepo
import java.util.logging.Logger

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
        LOG.info("loadProducts - started")
        return if (isApiAvailable()) {
            val categoriesIds = categoryRepo.findCategoriesIds()
            categoriesIds.forEach(::saveProducts)
            LOG.info("loadProducts - all products loaded")
            true
        } else {
            false
        }
    }

    private fun saveProducts(categoryId: String) {
        val products = httpService.getProducts(categoryId).data.map {
            it.copy(categoryId = categoryId)
        }
        productRepo.saveAll(products)
    }

    private fun isApiAvailable(): Boolean {
        return try {
            httpService.getCategory("0")
            LOG.info("isApiAvailable = true")
            true
        } catch (e: Exception) {
            LOG.info("isApiAvailable = false")
            false
        }
    }

    companion object {
        val LOG: Logger = Logger.getLogger(this::class.java.name)
    }
}
