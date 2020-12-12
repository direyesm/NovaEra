package com.example.novaera.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.novaera.model.local.entities.DetailsEnti
import com.example.novaera.model.local.entities.ProductsEnti
import com.example.novaera.model.remote.pojo.Products


@Dao
interface NovaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phoneList : List<ProductsEnti>)

    @Query("SELECT * FROM products_table")
    fun getPhone() : LiveData<List<ProductsEnti>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailPhone(phoneDetail : List<DetailsEnti>)

    @Query("SELECT * FROM details_table WHERE id=:mId")
    fun getAllDetail(mId: Int) : LiveData<DetailsEnti>
}