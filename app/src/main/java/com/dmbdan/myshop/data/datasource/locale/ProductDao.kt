package com.dmbdan.myshop.data.datasource.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmbdan.myshop.domain.model.ProductResponseItem

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(productResponseItem: ProductResponseItem)

    @Query("SELECT * FROM products")
    suspend fun getProducts() : List<ProductResponseItem>
}