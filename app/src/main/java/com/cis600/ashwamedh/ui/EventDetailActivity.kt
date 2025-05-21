package com.cis600.ashwamedh.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.databinding.ActivityEventDetailBinding

import com.cis600.ashwamedh.model.Event


class EventDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event = intent.getSerializableExtra("event") as Event

        // Set ActionBar title and enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = event.title
        Log.d("EventDetail", "Image URL: ${event.imageURL}")
        Glide.with(this)
            .load(event.imageURL)
            //.placeholder(R.drawable.event_banner) // optional
            //.error(R.drawable.error_image)             // optional
            .into(binding.eventBanner)
        binding.eventTitleText.text = event.title
        binding.eventDescText.text = event.description
        binding.eventVenue.text = "Venue: ${event.venue}"
        binding.eventDateTime.text = "${event.date} at ${event.time}"
        binding.eventRules.text = event.rules

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            intent.putExtra("eventName", event.title)
            startActivity(intent)
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
