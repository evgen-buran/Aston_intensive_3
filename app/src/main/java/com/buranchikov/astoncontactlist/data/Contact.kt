package com.buranchikov.astoncontactlist.data

import java.io.Serializable

data class Contact(
    val id: Int,
    val name: String,
    val secondName: String,
    val phone: String,
    val photoURL: String = "https://placekitten.com/100/100",
    val gender: String,
):Serializable
