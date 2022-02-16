package com.alassaneniang.kotlinsearch.models

import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column var id: Long = 0L,
    @Column var title: String = "",
    @Column var description: String = "",
    @Column var image: String = "",
    @Column var price: Double = 0.0,
)