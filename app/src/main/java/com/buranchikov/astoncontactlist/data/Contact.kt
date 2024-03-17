package com.buranchikov.astoncontactlist.data

import java.io.Serializable

data class Contact(
    val id: Int,
    val name: String,
    val secondName: String,
    val phone: String,
    var isSelected: Boolean = false
):Serializable
