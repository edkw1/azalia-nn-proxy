package ru.edkw.azalia.domain

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "products")
data class Product(
    @Id
    @JsonProperty("id")
    val id: String? = null,

    @JsonProperty("category_name")
    val categoryName: String? = null,

    @JsonProperty("discount_price")
    val discountPrice: Int? = null,

    @JsonProperty("count")
    val count: Int? = null,

    @JsonProperty("photo")
    val photo: String? = null,

    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("pack")
    val pack: Int? = null,

    @JsonProperty("article")
    val article: String? = null,

    @JsonProperty("colour")
    val colour: String? = null,

    @JsonProperty("category_id")
    val categoryId: String? = null,

    @JsonProperty("diameter")
    val diameter: Int? = null,

    @JsonProperty("price")
    val price: Int? = null,

    @JsonProperty("width")
    val width: Int? = null,

    @JsonProperty("comment")
    val comment: String? = null,

    @JsonProperty("height")
    val height: Int? = null,
)
