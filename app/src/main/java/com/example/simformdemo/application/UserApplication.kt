package com.example.simformdemo.application

import android.app.Application
import com.example.simformdemo.repository.UserRepository
import com.example.simformdemo.retrofit.ApiClient
import com.example.simformdemo.retrofit.ApiInterface
import com.example.simformdemo.roomdatabase.UserDatabase

class UserApplication : Application() {

    lateinit var userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiInterface = ApiClient.getInstance().create(ApiInterface::class.java)
        val userDataBase = UserDatabase.getInstance(applicationContext)
        userRepository = UserRepository(apiInterface, userDataBase, applicationContext)
    }


}