package com.example.simformdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simformdemo.adapter.UserAdapter
import com.example.simformdemo.application.UserApplication
import com.example.simformdemo.databinding.ActivityMainBinding
import com.example.simformdemo.repository.UserRepository
import com.example.simformdemo.retrofit.ApiClient
import com.example.simformdemo.retrofit.ApiInterface
import com.example.simformdemo.viewmodel.UserViewModel
import com.example.simformdemo.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {
    private var userAdapter: UserAdapter? = null
    lateinit var  viewModel : UserViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.rvUserList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val repository = (application as UserApplication).userRepository

        viewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewModel::class.java)

        viewModel.user.observe(this, {
            if (userAdapter == null) {
                userAdapter = UserAdapter(this, it)
                mainBinding.rvUserList.adapter = userAdapter
            } else {
                userAdapter?.updateList(it)
            }
            Log.e(TAG, "onCreate: it "+it.toString() )
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
        })
    }
}