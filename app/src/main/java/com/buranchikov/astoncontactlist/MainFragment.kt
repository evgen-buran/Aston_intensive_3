package com.buranchikov.astoncontactlist

import android.content.res.XmlResourceParser
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.buranchikov.astoncontactlist.data.Contact
import com.buranchikov.astoncontactlist.databinding.FragmentMainBinding
import org.xmlpull.v1.XmlPullParser

class MainFragment : Fragment() {
    val TAG = "myLog"
    private lateinit var binding: FragmentMainBinding
    private var contactsList = mutableListOf<Contact>()
    private val adapter = MainAdapter { contact ->
        Toast.makeText(requireContext(),"$contact",Toast.LENGTH_SHORT).show()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        contactsList = setContactsList()
        Log.d(TAG, "onStart: ${contactsList.size}")
        contactsList.forEach { Log.d(TAG, "onStart: $it ") }

        binding.mainRecyclerView.adapter = adapter
        adapter.submitList(contactsList)
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
    fun setVisibleFab(visible:Int) {
        binding.floatingActionButton.visibility  =visible
    }


}