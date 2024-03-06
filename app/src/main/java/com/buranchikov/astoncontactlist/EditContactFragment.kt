package com.buranchikov.astoncontactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buranchikov.astoncontactlist.databinding.FragmentDetailContactBinding
import com.buranchikov.astoncontactlist.databinding.FragmentNewContactBinding

class EditContactFragment : Fragment() {
    lateinit var binding: FragmentNewContactBinding
    private val OLD_CONTACT = "oldContact"
    private val EDITED_CONTACT = "editedContact"
    private val EDIT_CONTACT_REQUEST = "editContactRequest"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewContactBinding.inflate(inflater, container, false)
        binding.btnAddNewContact.text = getString(R.string.edit_btn_text)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

    }
}