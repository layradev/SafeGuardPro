package com.happyhour.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.happyhour.safeguardpro.service.model.Entrega
import com.happyhour.safeguardpro.service.repository.EntregaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntregaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EntregaRepository(application)

    private val mEntrega = MutableLiveData<Entrega>()
    val entrega: LiveData<Entrega> = mEntrega

    private val mCreatedEntrega = MutableLiveData<Entrega>()
    val createdEntrega: LiveData<Entrega> = mCreatedEntrega

    private val mUpdatedEntrega = MutableLiveData<Entrega>()
    val updatedEntrega: LiveData<Entrega> = mUpdatedEntrega

    private val mDeletedEntrega = MutableLiveData<Boolean>()
    val deletedEntrega: LiveData<Boolean> = mDeletedEntrega

    private val mEntregaList = MutableLiveData<List<Entrega>>()
    val entregaList: LiveData<List<Entrega>> = mEntregaList

    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEntregaList.postValue(repository.getEntregas())
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun getEntrega(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mEntrega.postValue(repository.getEntrega(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(entrega: Entrega) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEntrega = repository.insertEntrega(entrega)
                mCreatedEntrega.postValue(createdEntrega)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun update(entrega: Entrega) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedEntrega = repository.updateEntrega(entrega.id, entrega)
                mUpdatedEntrega.postValue(updatedEntrega)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedEntrega.postValue(repository.deleteEntrega(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }
}