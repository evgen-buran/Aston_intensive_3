package com.buranchikov.astoncontactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buranchikov.astoncontactlist.databinding.FragmentDetailContactBinding

class DetailContactFragment : Fragment() {
    lateinit var binding: FragmentDetailContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailContactBinding.inflate(inflater, container, false)
        return binding.root
    }
}