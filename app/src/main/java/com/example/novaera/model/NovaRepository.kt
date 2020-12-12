package com.example.novaera.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.novaera.model.local.dao.NovaDao
import com.example.novaera.model.local.entities.DetailsEnti
import com.example.novaera.model.local.entities.ProductsEnti
import com.example.novaera.model.remote.RetrofitClient
import com.example.novaera.model.remote.pojo.Details
import com.example.novaera.model.remote.pojo.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NovaRepository(private val novaDao: NovaDao) {

    private val service = RetrofitClient.novaEra()
    val allPhone = novaDao.getPhone()

    fun getDetailsPhone(id: Int): LiveData<DetailsEnti>{
        return novaDao.getAllDetail(id)
    }

    fun getPhoneFromApi() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.fetchProducts() }
        service.onSuccess {
            when(it.code()){
                in 200..299 -> it.body()?.let {
                   novaDao.insertPhone(convertPro(it))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
            service.onFailure {
                Log.e("ERROR", it.message.toString())
            }
        }
    }


    fun getDetailFromApi(id :Int) = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.fetchDetails(id) }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    novaDao.insertDetailPhone(convertDetail(it,id))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
            service.onFailure {
                Log.e("ERROR", it.message.toString())
            }
        }
    }

    fun convertPro(list: List<Products>): List<ProductsEnti>{
        val listMutable = mutableListOf<ProductsEnti>()
        list.map {
            listMutable.add(
                ProductsEnti(it.id,
            it.image,
            it.name,
            it.price)
            )
        }
        return listMutable
    }

    fun convertDetail(list: List<Details>, id: Int): List<DetailsEnti>{
        val listMutable = mutableListOf<DetailsEnti>()
        list.map {
            listMutable.add(
                DetailsEnti(it.credit,
            it.description,
            it.id,
            it.image,
            it.lastPrice,
            it.name,
            it.price)
            )
        }
        return listMutable
    }
}