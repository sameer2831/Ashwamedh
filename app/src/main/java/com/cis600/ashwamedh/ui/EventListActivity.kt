package com.cis600.ashwamedh.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.adapter.EventAdapter
import com.cis600.ashwamedh.databinding.ActivityEventListBinding
import com.cis600.ashwamedh.model.Event

import com.google.firebase.firestore.FirebaseFirestore


class EventListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventListBinding
    private val eventList = ArrayList<Event>()
    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setup Bottom Navigation
        binding.bottomNav.selectedItemId = R.id.nav_events
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.nav_events -> true
                    // Already here

                R.id.nav_register -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    true
                }
                R.id.nav_contact -> {
                    startActivity(Intent(this, ContactActivity::class.java))
                    true
                }
                else -> false
            }
        }
        adapter = EventAdapter(this,eventList)

        binding.eventRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.eventRecyclerView.adapter = adapter

        fetchEvents()
    }

    private fun fetchEvents() {
        FirebaseFirestore.getInstance().collection("events")
            .get().addOnSuccessListener { result ->
                eventList.clear()
                for (doc in result) {
                    val event = doc.toObject(Event::class.java)
                    eventList.add(event)
                }
                adapter.notifyDataSetChanged()
            }
    }
}
