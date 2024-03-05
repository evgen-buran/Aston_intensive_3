package com.buranchikov.astoncontactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.buranchikov.astoncontactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    private val mainFragment = MainFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        fragmentManager = supportFragmentManager
        getFragment(mainFragment)

        binding.btnCancel.setOnClickListener {
            binding.btnDelete.visibility = View.GONE
            binding.btnCancel.visibility = View.GONE
            mainFragment.setVisibleFab(View.VISIBLE)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.btnCancel.visibility = View.VISIBLE
        binding.btnDelete.visibility = View.VISIBLE
        mainFragment.setVisibleFab(View.INVISIBLE)
        return true
    }

    private fun getFragment(fragment:Fragment) {
        fragmentManager.beginTransaction().replace(
            R.id.fragmentContainerView,
           fragment
        ).commit()
    }
}
