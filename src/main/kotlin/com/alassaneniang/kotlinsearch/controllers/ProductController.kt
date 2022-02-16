package com.alassaneniang.kotlinsearch.controllers

import com.alassaneniang.kotlinsearch.models.Product
import com.alassaneniang.kotlinsearch.models.dtos.PaginatedResponse
import com.alassaneniang.kotlinsearch.repositories.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping("/frontend")
    fun frontend(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productRepository.findAll())
    }

    @GetMapping("/backend")
    fun backend(
        @RequestParam("search", defaultValue = "") search: String,
        @RequestParam("sortby", defaultValue = "price") sortBy: String,
        @RequestParam("direction", defaultValue = "") direction: String,
        @RequestParam("page", defaultValue = "1") page: Int,
    ): ResponseEntity<Any> {
        var sorting = Sort.unsorted()
        if (direction == "asc") {
            sorting = Sort.by(Sort.Direction.ASC, sortBy)
        } else if (direction == "desc") {
            sorting = Sort.by(Sort.Direction.DESC, sortBy)
        }
        val initPage = page - 1
        val perPage = 10
        val total = productRepository.countSearch(search)

        return ResponseEntity.ok(
            PaginatedResponse(
                data = productRepository.search(search, PageRequest.of(initPage, perPage, sorting)),
                total = total,
                page = page,
                lastPage = (total / perPage) + 1,
            )
        )
    }

}