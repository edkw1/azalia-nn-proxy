package ru.edkw.azalia.rest

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.edkw.azalia.dto.BaseResponse
import ru.edkw.azalia.service.SyncService

@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/api/v1/sync")
class EntitiesSyncRestController(
    private val syncService: SyncService
) {

    @GetMapping("/")
    fun loadData(): BaseResponse {
        return BaseResponse(status = syncService.syncData())
    }
}