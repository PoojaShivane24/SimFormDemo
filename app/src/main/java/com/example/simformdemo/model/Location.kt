package com.example.simformdemo.model

import java.io.Serializable

data class Location(val city : String, val state : String, val country : String, val postcode : String) : Serializable