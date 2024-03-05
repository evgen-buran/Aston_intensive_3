package com.buranchikov.astoncontactlist

import android.content.res.XmlResourceParser
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.buranchikov.astoncontactlist.data.Contact
import com.buranchikov.astoncontactlist.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val CURRENT_ID = "currentID"
    private val NEW_CONTACT_REQUEST = "newContactRequest"
    private val EDIT_CONTACT_REQUEST = "editContactRequest"
    private val CONTACT = "contact"
    private lateinit var binding: FragmentMainBinding
    private var contactsList = mutableListOf<Contact>()
    private val adapter = MainAdapter { contact ->

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        contactsList = setContactsList()
        setFragmentResultListener(NEW_CONTACT_REQUEST) { _, bundle ->
            val contact = bundle.getSerializable(CONTACT) as Contact
            contactsList.add(contact)
            adapter.submitList(contactsList)
        }
        binding.mainRecyclerView.adapter = adapter
        adapter.submitList(contactsList)

        binding.floatingActionButton.setOnClickListener {
            navigateEditOrNewFragment()
        }
    }

    private fun navigateEditOrNewFragment() {
        val bundle = Bundle().apply {
            putInt(CURRENT_ID, contactsList.size)
        }

        val newContactFragment = NewContactFragment()
        newContactFragment.arguments = bundle
        val fragmentManager = parentFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, newContactFragment)
            .addToBackStack(null)
            .commit()
    }


    private fun setContactsList(): MutableList<Contact> {
        val dataXml = resources.getXml(R.xml.contacts_xml_small)
        val listContact = mutableListOf<Contact>()

        var id = 0
        var photoUrl = ""
        var name = ""
        var secondName = ""
        var phone = ""
        var gender = ""

        while (dataXml.eventType != XmlResourceParser.END_DOCUMENT) {
            when (dataXml.eventType) {
                XmlResourceParser.START_TAG -> {
                    when (dataXml.name) {
                        "item" -> {
                            id = 0
                            photoUrl = ""
                            name = ""
                            secondName = ""
                            phone = ""
                            gender = ""
                        }

                        "id" -> id = dataXml.nextText().toInt()
                        "photo_url" -> photoUrl = dataXml.nextText()
                        "first_name" -> name = dataXml.nextText()
                        "last_name" -> secondName = dataXml.nextText()
                        "phone" -> phone = dataXml.nextText()
                        "gender" -> gender = dataXml.nextText()
                    }
                }

                XmlResourceParser.END_TAG -> {
                    if (dataXml.name == "item") {
                        listContact.add(
                            Contact(
                                id = id,
                                name = name,
                                secondName = secondName,
                                phone = phone,
                                photoURL = photoUrl,
                                gender = gender
                            )
                        )
                    }
                }
            }
            dataXml.next()
        }
        return listContact
    }

    fun setVisibleFab(visible: Int) {
        binding.floatingActionButton.visibility = visible
    }


}