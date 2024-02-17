package ru.edkw.azalia.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.edkw.azalia.domain.Category
import ru.edkw.azalia.repo.CategoryRepo

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
        categoryRepo.deleteAll()
        saveCategory(ROOT_ID)
        return true
    }

    private fun saveCategory(categoryId: String) {
        val category = httpService.getCategory(categoryId).let {
            if (it.id == ROOT_ID) it.copy(parentId = null) else it
        }
        categoryRepo.save(category)
        category.childId.forEach(::saveCategory)
    }
}
