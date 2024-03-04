package com.buranchikov.astoncontactlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.buranchikov.astoncontactlist.databinding.FragmentNewContactBinding

class NewContactFragment : Fragment() {
    lateinit var binding: FragmentNewContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewContactBinding.inflate(inflater, container, false)
        return binding.root
    }
}