package com.example.novaera.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.novaera.model.local.dao.NovaDao
import com.example.novaera.model.local.entities.DetailsEnti
import com.example.novaera.model.local.entities.ProductsEnti

@Database(entities = [ProductsEnti::class, DetailsEnti::class], version = 1)
abstract class NovaEraDatabase : RoomDatabase(){

    abstract fun getNovaDao(): NovaDao


    companion object{
        @Volatile
        private var INSTANCE:  NovaEraDatabase? = null

        fun  getDatabase(context: Context) : NovaEraDatabase{
            val tempInstance =  INSTANCE
            if (tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NovaEraDatabase::class.java,
                    "Nova_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}