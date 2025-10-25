package com.example.lab09

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab09.Product
import com.example.lab09.ProductRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class ProductViewModel : ViewModel() {
    private val repo = ProductRepository()

    // lista observable para Compose
    val productList: SnapshotStateList<Product> = mutableStateListOf()

    // estado para detalle
    private val _selectedProduct = mutableStateOf<Product?>(null)
    val selectedProduct: State<Product?> = _selectedProduct

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = repo.fetchProducts()
                productList.addAll(products)
            } catch (e: Exception) {
                // maneja error (log/estado) según convenga
                e.printStackTrace()
            }
        }
    }

    fun loadProductById(id: Int) {
        viewModelScope.launch {
            try {
                val p = repo.fetchProductById(id)
                _selectedProduct.value = p
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
