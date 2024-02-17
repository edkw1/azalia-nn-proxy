package ru.edkw.azalia.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "categories")
data class Category(
    @JsonProperty("id")
    @Id
    @JsonView(Views.Category::class)
    val id: String,

    @JsonView(Views.Category::class)
    @JsonProperty("title")
    val title: String? = null,

    @JsonView(Views.Category::class)
    @JsonProperty("parent_id")
    val parentId: String? = null,

    @JsonView(Views.Category::class)
    @Transient
    @JsonProperty("child_id")
    val childId: List<String> = emptyList(),

    @JsonView(Views.Category::class)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    val categories: List<Category> = emptyList(),

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    val products: List<Product> = emptyList(),

    @JsonView(Views.Category::class)
    @JsonProperty("photo")
    val photo: String? = null,
)
