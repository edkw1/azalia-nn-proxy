package ru.edkw.azalia.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "products")
data class Product(
    @JsonView(Views.Product::class)
    @Id
    @JsonProperty("id")
    val id: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("category_name")
    val categoryName: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("discount_price")
    val discountPrice: Int? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("count")
    val count: Int? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("photo")
    val photo: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("description")
    val description: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("title")
    val title: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("pack")
    val pack: Int? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("article")
    val article: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("colour")
    val colour: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("category_id")
    val categoryId: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("diameter")
    val diameter: Int? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("price")
    val price: Int? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("width")
    val width: Int? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("comment")
    val comment: String? = null,

    @JsonView(Views.Product::class)
    @JsonProperty("height")
    val height: Int? = null,
)
