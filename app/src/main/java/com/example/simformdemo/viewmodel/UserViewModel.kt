package com.example.simformdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simformdemo.model.Results
import com.example.simformdemo.model.User
import com.example.simformdemo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserList()
        }
    }

    val user : LiveData<List<Results>?>
        get() {
            return repository.user
        }

}