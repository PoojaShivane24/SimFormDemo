package com.example.simformdemo.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simformdemo.model.Results
import com.example.simformdemo.model.User
import com.example.simformdemo.retrofit.ApiInterface
import com.example.simformdemo.roomdatabase.UserDatabase
import com.example.simformdemo.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val apiInterface: ApiInterface,
    private val userDataBase: UserDatabase,
    private val context: Context
) {

    private val userLiveData = MutableLiveData<List<Results>?>()
    val user : LiveData<List<Results>?>
        get() {
            return userLiveData
        }
    private val TAG = "UserRepository"
    val coroutineScope = CoroutineScope(SupervisorJob())

    suspend fun getUserList() {

        if (NetworkUtils.isInternetAvailable(context) == true) {
            Log.e(TAG, "getUserList: true ", )

            val call = apiInterface.getUserList(100)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Log.e(TAG, "onResponse: "+response.body())
                    Log.e(TAG, "onResponse: "+call.request().url() )
                    val user = response.body()
                    val userList = user?.results
                    if (userList != null) {
                        for (user in userList) {
                            coroutineScope.launch {
                                val entity = Results(
                                    user.gender,
                                    user.name,
                                    user.location,
                                    user.email,
                                    user.dob,
                                    user.phone,
                                    user.picture
                                )

                                val count = userDataBase.getUserDetailDao().insert(entity)
                                userLiveData.postValue(userList)
                                if (count > 0L) {
                                    Log.e(TAG, "storeData: successfully ",)
//                            getMovieList(context)
                                } else {
                                    Log.e(TAG, "storeData: already present ",)
                                }
                            }
                        }

                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {

                }

            })

        } else {
            Log.e(TAG, "getUserList: false",)

            val userList = userDataBase.getUserDetailDao().getUserList()
            userLiveData.postValue(userList)
        }

    }
}