package com.coded.petui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coded.petui.model.Pet
import com.coded.petui.repository.PetRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class PetViewModel : ViewModel() {

    private val repository = PetRepository()

    private val _petList = mutableStateOf<List<Pet>>(emptyList())
    val petList: State<List<Pet>> = _petList

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        fetchPets()
    }

    private fun fetchPets() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val pets = repository.getPets()
                _petList.value = pets
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
