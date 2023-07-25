package com.dmbdan.myshop.data.datasource.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dmbdan.myshop.data.db.Converter
import com.dmbdan.myshop.domain.model.ProductResponseItem

@Database(entities = [ProductResponseItem::class], version = 1)
@TypeConverters(Converter::class)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun productDao() : ProductDao
}