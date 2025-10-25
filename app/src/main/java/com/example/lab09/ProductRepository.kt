package com.example.lab09

import com.example.lab09.Product
import com.example.lab09.RetrofitInstance

class ProductRepository {
    private val api = RetrofitInstance.api

    suspend fun fetchProducts() = api.getProducts().products

    suspend fun fetchProductById(id: Int): Product = api.getProductById(id)
}
