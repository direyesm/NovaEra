package com.example.novaera.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.novaera.model.local.NovaEraDatabase
import com.example.novaera.model.local.dao.NovaDao
import com.example.novaera.model.local.entities.ProductsEnti
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var  mNovaDao: NovaDao
    private lateinit var  db : NovaEraDatabase

    @Before
    fun setUo(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NovaEraDatabase::class.java).build()
        mNovaDao = db.getNovaDao()
    }

    @After
    fun showDown(){
        db.close()
    }

    @Test
    fun insertListElement_happy_case() = runBlocking {
        //given
        val phoneList = listOf(ProductsEnti(1, "aaaaa", "aaaaa", 1500000))
        //when
        mNovaDao.insertPhone(phoneList)
        //then
        mNovaDao.getPhone().observeForever {
            assertThat(it.isNotEmpty())
            assertThat(it[0].id).isEqualTo("1")
            assertThat(it).hasSize(1)

        }
    }


}