package ru.edkw.azalia.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.edkw.azalia.domain.Category

@Repository
interface CategoryRepo : JpaRepository<Category, String> {

    fun findAllByParentId(parentId: String?): List<Category>

    @Query("SELECT category.id FROM Category category")
    fun findCategoriesIds(): List<String>
}
