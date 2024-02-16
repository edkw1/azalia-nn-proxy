package ru.edkw.azalia.domain

import com.fasterxml.jackson.annotation.JsonProperty
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
    val id: String,

    @JsonProperty("title")
    val title: String? = null,

    @JsonProperty("parent_id")
    val parentId: String? = null,

    @Transient
    @JsonProperty("child_id")
    val childId: List<String> = emptyList(),

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    val categories: List<Category> = emptyList(),

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    val products: List<Product> = emptyList(),

    @JsonProperty("photo")
    val photo: String? = null,
)
