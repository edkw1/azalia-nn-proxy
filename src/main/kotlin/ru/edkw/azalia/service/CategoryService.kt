package ru.edkw.azalia.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.edkw.azalia.domain.Category
import ru.edkw.azalia.repo.CategoryRepo
import java.util.logging.Logger

private const val ROOT_ID = "0"

@Service
class CategoryService(
    private val categoryRepo: CategoryRepo,
    private val httpService: HttpService
) {

    @Cacheable("categories")
    fun getCategory(categoryId: String?): Category? {
        return categoryRepo.findByIdOrNull(categoryId)
    }

    @CacheEvict("categories")
    fun loadCategories(): Boolean {
        ProductService.LOG.info("loadCategories - started")
        return if (isApiAvailable()) {
            categoryRepo.deleteAll()
            saveCategory(ROOT_ID)
            ProductService.LOG.info("loadCategories - all categories saved")
            true
        } else {
            false
        }
    }

    private fun isApiAvailable(): Boolean {
        return try {
            httpService.getCategory(ROOT_ID)
            LOG.info("isApiAvailable = true")
            true
        } catch (e: Exception) {
            LOG.info("isApiAvailable = false")
            false
        }
    }

    private fun saveCategory(categoryId: String) {
        val category = httpService.getCategory(categoryId).let {
            if (it.id == ROOT_ID) it.copy(parentId = null) else it
        }
        categoryRepo.save(category)
        category.childId.forEach(::saveCategory)
    }

    companion object {
        val LOG: Logger = Logger.getLogger(this::class.java.name)
    }
}
