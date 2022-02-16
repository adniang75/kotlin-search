package com.alassaneniang.kotlinsearch.models.dtos

class PaginatedResponse(
    val data: List<Any>,
    val total: Int,
    val page: Int,
    val lastPage: Int,
)