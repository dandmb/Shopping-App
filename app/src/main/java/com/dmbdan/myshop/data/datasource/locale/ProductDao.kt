package com.dmbdan.myshop.data.datasource.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dmbdan.myshop.domain.model.ProductResponseItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(productResponseItem: ProductResponseItem)

    @Query("SELECT * FROM products")
    fun getProducts() : Flow<List<ProductResponseItem>>
}