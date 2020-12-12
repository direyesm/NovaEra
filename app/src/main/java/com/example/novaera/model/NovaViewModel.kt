package com.example.novaera.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.novaera.model.local.NovaEraDatabase
import com.example.novaera.model.local.entities.DetailsEnti
import com.example.novaera.model.local.entities.ProductsEnti


class NovaViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: NovaRepository
    val phoneLivedata : LiveData<List<ProductsEnti>>

    init {
        val mDao = NovaEraDatabase.getDatabase(application).getNovaDao()
        mRepository = NovaRepository(mDao)
        phoneLivedata = mRepository.allPhone
        mRepository.getPhoneFromApi()
    }

    fun getDetails(id: Int): LiveData<DetailsEnti>{
        mRepository.getDetailFromApi(id)
        return mRepository.getDetailsPhone(id)
    }

}