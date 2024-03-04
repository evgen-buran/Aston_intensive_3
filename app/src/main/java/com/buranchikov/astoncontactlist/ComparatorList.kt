package com.buranchikov.astoncontactlist

import androidx.recyclerview.widget.DiffUtil
import com.buranchikov.astoncontactlist.data.Contact

class ComparatorList:DiffUtil.ItemCallback<Contact> (){
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        TODO("Not yet implemented")
    }

}
