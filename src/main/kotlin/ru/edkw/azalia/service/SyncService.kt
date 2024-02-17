package ru.edkw.azalia.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class SyncService(
    private val categoryService: CategoryService,
    private val productService: ProductService
) {
    @Scheduled(cron = "0 0 0 * * *")
    fun syncData(): Boolean {
        try {
            categoryService.loadCategories()
            productService.loadProducts()
        } catch (e: Exception) {
            LOG.info("syncData = failed")
            e.printStackTrace()
            return false
        }
        LOG.info("syncData = success")
        return true
    }

    companion object {
        val LOG: Logger = Logger.getLogger(this::class.java.name)
    }
}