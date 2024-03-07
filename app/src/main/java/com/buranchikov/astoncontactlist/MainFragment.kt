package com.buranchikov.astoncontactlist

import android.content.res.XmlResourceParser
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.buranchikov.astoncontactlist.data.Contact
import com.buranchikov.astoncontactlist.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val LAST_ID = "lastId"
    private val NEW_CONTACT = "newContact"
    private val NEW_CONTACT_REQUEST = "newContactRequest"
    private val OLD_CONTACT = "oldContact"
    private lateinit var binding: FragmentMainBinding
    private var contactsList = mutableListOf<Contact>()

    private val adapter = MainAdapter { contact ->
        editContact(contact)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        if (contactsList.isEmpty()) contactsList = setContactsListFromXML()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setFragmentResultListener(NEW_CONTACT_REQUEST) { _, bundle ->
           val newContact =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
              bundle.getSerializable(NEW_CONTACT, Contact::class.java)
           } else  bundle.getSerializable(NEW_CONTACT) as Contact

            var isReplace = false
            for (i in 0 until contactsList.size) {
                if (contactsList[i].id == newContact?.id) {
                    contactsList[i] = newContact
                    isReplace = true
                }
            }
            if (!isReplace) contactsList.add(newContact!!)
            adapter.submitList(contactsList)
        }
        binding.mainRecyclerView.adapter = adapter
        adapter.submitList(contactsList)

        binding.floatingActionButton.setOnClickListener {
            createNewContact()
        }
    }

    private fun createNewContact() {
        val bundle = Bundle().apply {
            putInt(LAST_ID, contactsList.size)
        }
        navigateFragment(bundle)
    }

    private fun editContact(contact: Contact) {
        val bundle = Bundle().apply {
            putSerializable(OLD_CONTACT, contact)
        }
        navigateFragment(bundle)
    }

    private fun navigateFragment(bundle: Bundle) {
        val newContactFragment = NewContactFragment()
        newContactFragment.arguments = bundle
        val fragmentManager = parentFragmentManager

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, newContactFragment)
            .addToBackStack(null)
            .commit()
    }
    private fun setContactsListFromXML(): MutableList<Contact> {
        val dataXml = resources.getXml(R.xml.contacts_xml)
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