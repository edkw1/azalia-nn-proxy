package ru.edkw.azalia.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.client.methods.RequestBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.edkw.azalia.domain.Category
import ru.edkw.azalia.dto.ProductsData

@Service
class HttpService(
    val mapper: ObjectMapper,
    @Value("\${ru.edkw.api-url}") val apiUrl: String
) {

    fun getProducts(category: String?): ProductsData {
        return get("/get-products-list/${category}")
    }

    fun getCategory(category: String): Category {
        return get("/get-categories-list/${category}")
    }

    final inline fun <reified T> get(query: String): T {
        val newQuery = query.apply {
            replace(" ".toRegex(), "%20")
            replace(":".toRegex(), "%3A")
            replace(";".toRegex(), "%3B")
        }
        val httpClient = HttpClients.createDefault()
        val request = RequestBuilder.get(apiUrl + newQuery).build()
        val response = httpClient.execute(request)
        val json = EntityUtils.toString(response.entity)
        return mapper.readValue(json, T::class.java)
    }
}