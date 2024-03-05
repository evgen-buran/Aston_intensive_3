package com.buranchikov.astoncontactlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.buranchikov.astoncontactlist.data.Contact
import com.buranchikov.astoncontactlist.databinding.FragmentNewContactBinding

class NewContactFragment : Fragment() {

    val TAG = "myLog"

    private val CURRENT_ID: String = "currentID"
    private val NEW_CONTACT_REQUEST = "newContactRequest"
    private val CONTACT = "contact"

    lateinit var binding: FragmentNewContactBinding

    private var id = 0
    private var name = ""
    private var secondName = ""
    private var phone = ""
    private var gender = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rbMan.setOnClickListener { onRadioButtonClick(it) }
        binding.rbWoman.setOnClickListener { onRadioButtonClick(it) }

    }


    override fun onStart() {
        super.onStart()
        binding.btnAddNewContact.setOnClickListener {
            id = arguments?.getInt(CURRENT_ID)!!
            name = binding.etNameNew.text.toString()
            secondName = binding.etSecondNameNew.text.toString()
            phone = binding.etPhone.text.toString()
            val contact = Contact(
                id = ++id,
                name = name,
                secondName = secondName,
                phone = phone,
                gender = gender
            )
            val bundle = Bundle().apply {
                putSerializable(CONTACT, contact)
            }

            setFragmentResult(NEW_CONTACT_REQUEST, bundle)
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }
    }

    private fun onRadioButtonClick(view: View) {
        val radioButton = view as RadioButton
        if (radioButton.isChecked) {
            when (radioButton.text) {
                getString(R.string.man_gender) -> gender = getString(R.string.man_gender)
                getString(R.string.woman_gender) -> gender = getString(R.string.woman_gender)
            }
        }
    }
}