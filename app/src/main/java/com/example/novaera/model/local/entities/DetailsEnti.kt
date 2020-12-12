package com.example.novaera.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "details_table")
class DetailsEnti (val credit: Boolean,
                   val description: String,
                   @PrimaryKey val id: Int,
                   val image: String,
                   val lastPrice: Int,
                   val name: String,
                   val price: Int
                   )