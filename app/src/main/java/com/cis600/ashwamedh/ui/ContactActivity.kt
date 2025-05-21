package com.cis600.ashwamedh.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.adapter.ContactAdapter
import com.cis600.ashwamedh.databinding.ActivityContactBinding
import com.cis600.ashwamedh.model.Contact
import com.google.firebase.firestore.FirebaseFirestore

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var contactAdapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        setupRecyclerView()
        loadContactCards()
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.selectedItemId = R.id.nav_contact
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_events -> {
                    startActivity(Intent(this, EventListActivity::class.java))
                    true
                }
                R.id.nav_register -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    true
                }
                R.id.nav_contact -> true
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        contactAdapter = ContactAdapter(contactList)
        binding.contactRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ContactActivity)
            adapter = contactAdapter
        }
    }

    private fun loadContactCards() {
        db.collection("team")
            .get()
            .addOnSuccessListener { result ->
                contactList.clear()
                for (document in result) {
                    val name = document.getString("name") ?: "Name not available"
                    val phone = document.getString("phn_number") ?: "Phone not available"
                    val email = document.getString("email") ?: "Email not available"
                    val image= document.getString("image") ?:"Image not available"

                    contactList.add(Contact(name, phone, email,image))
                }
                contactAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error loading contacts: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
