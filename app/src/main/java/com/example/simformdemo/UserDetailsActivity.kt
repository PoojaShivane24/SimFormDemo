package com.example.simformdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.simformdemo.databinding.ActivityUserDetailsBinding
import com.example.simformdemo.model.Results

class UserDetailsActivity : AppCompatActivity() {
    private var user : Results? = null
    private val TAG = "UserDetailsActivity"
    lateinit var userDetailsBinding: ActivityUserDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDetailsBinding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(userDetailsBinding.root)


        getIncomingIntent()

        setDataOnView(user)

    }

    private fun setDataOnView(user: Results?) {
        Glide.with(this).load(user?.picture?.large).into(userDetailsBinding.ivPicture)
        var name = user?.name?.title + " " + user?.name?.first + " " + user?.name?.last
        userDetailsBinding.collapsingToolbar.title = name

        userDetailsBinding.tvState.text = user?.location?.state
        userDetailsBinding.tvAge.text = user?.dob?.age
        userDetailsBinding.tvCity.text = user?.location?.city
        userDetailsBinding.tvCountry.text = user?.location?.country
        userDetailsBinding.tvGender.text = user?.gender
        userDetailsBinding.tvEmail.text = user?.email
        userDetailsBinding.tvPhone.text = user?.phone
        userDetailsBinding.tvPostcode.text = user?.location?.postcode

    }

    private fun getIncomingIntent() {
        if (intent != null) {
            if (intent.hasExtra("user"))
                user = intent.getSerializableExtra("user") as Results
        }
    }
}