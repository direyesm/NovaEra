package com.example.novaera.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "products_table")
class ProductsEnti (@PrimaryKey val id: Int,
                     val image: String,
                     val name: String,
                     val price: Int
)