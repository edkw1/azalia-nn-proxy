package ru.edkw.azalia.dto

import com.fasterxml.jackson.annotation.JsonView
import ru.edkw.azalia.domain.Product
import ru.edkw.azalia.domain.Views

data class ProductsData(

    @JsonView(Views.Product::class)
    val data: List<Product>
)
