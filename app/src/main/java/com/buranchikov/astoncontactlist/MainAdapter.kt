package com.buranchikov.astoncontactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.buranchikov.astoncontactlist.data.Contact
import com.buranchikov.astoncontactlist.databinding.ContactItemBinding

class MainAdapter(private val onClickAction: (Contact) -> Unit) :
    ListAdapter<Contact, MainAdapter.ContactViewHolder>(ComparatorList()) {


    class ContactViewHolder(private val binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.tvIdContactItem.text = contact.id.toString()
            binding.tvNameItem.text = contact.name
            binding.tvSecondNameItem.text = contact.secondName
            binding.tvPhoneItem.text = contact.phone
            binding.ivPhotoItem.load(contact.photoURL) {
                scale(Scale.FILL).size(
                    binding.root.resources.getDimension(R.dimen.avatar_size_big).toInt()
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener {
            val model = getItem(viewType)
            onClickAction(model)
        }
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class Comparator : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }
}