package com.example.simformdemo.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simformdemo.R
import com.example.simformdemo.UserDetailsActivity
import com.example.simformdemo.databinding.UserItemBinding
import com.example.simformdemo.model.Results
import java.io.Serializable

class UserAdapter(context: Context, userList : List<Results>?) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() , Serializable {

    private var userList : MutableList<Results> = ArrayList()
    private var context : Context
    init {
        if (userList != null) {
            this.userList.addAll(userList)
        }
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userItemBinding : UserItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.user_item, parent, false)
        return UserViewHolder(userItemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        val fullName = user.name.title + ". " +user.name.first + " " +user.name.last
        holder.view.tvTitle.text = fullName
        holder.view.tvEmail.text = user.email
        holder.view.tvGender.text = user.gender
        holder.view.tvAge.text = user.dob.age
        Glide.with(context).load(user.picture.medium).placeholder(R.drawable.ic_round_person_24).into(holder.view.ivPicture)

        holder.view.root.setOnClickListener {
            Log.e("TAG", "onBindViewHolder: "+userList )
            Log.e("TAG", "onBindViewHolder: "+user )

            val intent = Intent(context, UserDetailsActivity::class.java).apply {
                putExtra("user", user)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateList(list: List<Results>?) {
        if (list != null) {
            this.userList.addAll(0, list)
            notifyItemRangeChanged(0, list.size)
        }
    }

    class UserViewHolder(val view : UserItemBinding) : RecyclerView.ViewHolder(view.root)
}