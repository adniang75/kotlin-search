package com.alassaneniang.kotlinsearch.repositories

import com.alassaneniang.kotlinsearch.models.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Long> {

    @Query("SELECT COUNT(p) FROM Product p WHERE p.title LIKE %?1% OR p.description LIKE %?1%", countQuery = "*")
    fun countSearch(search: String): Int

    @Query("SELECT p FROM Product p WHERE p.title LIKE %?1% OR p.description LIKE %?1%")
    fun search(search: String, pageable: Pageable): List<Product>

}