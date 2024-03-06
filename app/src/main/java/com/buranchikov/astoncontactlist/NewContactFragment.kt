package com.buranchikov.astoncontactlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.buranchikov.astoncontactlist.data.Contact
import com.buranchikov.astoncontactlist.databinding.FragmentNewContactBinding

class NewContactFragment : Fragment() {
    private val LAST_ID = "lastId"
    private val NEW_CONTACT = "newContact"
    private val NEW_CONTACT_REQUEST = "newContactRequest"
    val TAG = "myLog"
    lateinit var binding: FragmentNewContactBinding
    private var id = 0
    private var name = ""
    private var secondName = ""
    private var phone = ""
    private var gender = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
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
        id = arguments?.getInt(LAST_ID)!!


        binding.btnAddNewContact.setOnClickListener {
            name = binding.etNameNew.text.toString()
            secondName = binding.etSecondNameNew.text.toString()
            phone = binding.etPhone.text.toString()
            val newContact = Contact(
                id = ++id, name = name, secondName = secondName, phone = phone, gender = gender
            )
            val bundle = Bundle()
            bundle.putSerializable(NEW_CONTACT, newContact)
            setFragmentResult(NEW_CONTACT_REQUEST, bundle)


            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }
    }


//    private fun settingEditFragmentListener() {
//        setFragmentResultListener(EDIT_CONTACT_REQUEST) { _, bundle ->
//            if (bundle.getSerializable(OUTPUT_EDIT_CONTACT) as? Contact != null) {
//                binding.btnAddNewContact.text = getString(R.string.edit_btn_text)
//
//                currentContact = arguments?.getSerializable(OUTPUT_EDIT_CONTACT) as Contact
//                binding.etNameNew.setText(currentContact.name)
//                binding.etSecondNameNew.setText(currentContact.secondName)
//                binding.etPhone.setText(currentContact.phone)
//                when (currentContact.gender) {
//                    getString(R.string.man_gender) -> binding.rbMan.isChecked = true
//                    getString(R.string.woman_gender) -> binding.rbWoman.isChecked = true
//                }
//            }
//        }
//    }

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