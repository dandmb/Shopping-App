package com.dmbdan.myshop.di

import android.content.Context
import androidx.room.Room
import com.dmbdan.myshop.data.datasource.locale.ProductDao
import com.dmbdan.myshop.data.datasource.locale.ProductDatabase
import com.dmbdan.myshop.data.datasource.remote.ProductsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesProductsAPi(retrofit: Retrofit): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }


    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "products.db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesProductsDao(database: ProductDatabase):ProductDao=database.productDao()



}
/*
Room.databaseBuilder(
context.applicationContext,
ProductDatabase::class.java,
"products.db"
).build()
        */