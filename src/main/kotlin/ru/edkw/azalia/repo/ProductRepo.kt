package ru.edkw.azalia.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.edkw.azalia.domain.Product

@Repository
interface ProductRepo : JpaRepository<Product, String> {

    fun findAllByCategoryId(categoryId: String): List<Product>

    fun findAllByCategoryIdIn(categoriesIds: List<String>): List<Product>
}
