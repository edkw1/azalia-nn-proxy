package ru.edkw.azalia.dto

data class BaseResponse(
    val message: String
) {
    constructor(status: Boolean) : this(if (status) "ok" else "error")
}
