package com.happyhour.safeguardpro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.happyhour.safeguardpro.service.model.Epi
import com.happyhour.safeguardpro.service.model.Funcionario
import com.happyhour.safeguardpro.service.repository.FuncionarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FuncionarioViewModel(application: Application) : AndroidViewModel(application) {
    //chama repository
    private val repository = FuncionarioRepository(application)

    //Cria a variavel observavel para jogar na tela
    private val mFuncionario = MutableLiveData<Funcionario>()
    var funcionario: LiveData<Funcionario> = mFuncionario

    //Cria a variavel observavel para jogar um erro na tela
    private val mErro = MutableLiveData<String>()
    val erro: LiveData<String> = mErro

    private val mDeletedFuncionario = MutableLiveData<Boolean>()
    var deletedFuncionario: LiveData<Boolean> = mDeletedFuncionario

    private val mFuncionarioList = MutableLiveData<List<Funcionario>>()
    val funcionarioList: LiveData<List<Funcionario>> = mFuncionarioList

    fun getFuncionarios(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                mFuncionarioList.postValue(repository.getFuncionarios())
            }catch (e: Exception){
                mErro.postValue(e.message)
            }
        }
    }

    fun getFuncionario(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mFuncionario.postValue(repository.getFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mDeletedFuncionario.postValue(repository.deleteFuncionario(id))
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

    fun insert(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val createdEpi = repository.insertFuncionario(funcionario)
                mCreatedFuncionario.postValue(createdFuncionario)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }


    fun update(funcionario: Funcionario) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val updatedFuncionario = repository.updateFuncionario(funcionario.id, funcionario)
                mUpdatedFuncionario.postValue(updatedFuncionario)
            } catch (e: Exception) {
                mErro.postValue(e.message)
            }
        }
    }

}