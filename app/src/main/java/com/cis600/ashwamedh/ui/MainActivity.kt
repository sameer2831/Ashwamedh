package com.cis600.ashwamedh.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.adapter.HotEventsAdapter
import com.cis600.ashwamedh.adapter.ImageSliderAdapter
import com.cis600.ashwamedh.databinding.ActivityMainBinding
import com.cis600.ashwamedh.model.Event
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var imageHandler: Handler
    private lateinit var imageRunnable: Runnable
    private lateinit var adapter: HotEventsAdapter
    private val hotEventList = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchEvents()
        setupImageSlider()
        setupHotEventsRecycler()
        setupBottomNavigation()
        binding.btnDirections.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }



    }

    private fun setupImageSlider() {
        val imageList = listOf(
            R.drawable.aissms,
            R.drawable.event_banner,
            R.drawable.event_banner,
            R.drawable.event_banner
        )

        binding.imageSlider.adapter = ImageSliderAdapter(imageList)

        imageHandler = Handler(Looper.getMainLooper())
        imageRunnable = Runnable {
            binding.imageSlider.currentItem =
                (binding.imageSlider.currentItem + 1) % imageList.size
        }

        binding.imageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                imageHandler.removeCallbacks(imageRunnable)
                imageHandler.postDelayed(imageRunnable, 3000)
            }
        })
    }

    private fun setupHotEventsRecycler() {
        binding.recyclerHotEvents.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        Log.d("HotEventsList", hotEventList.size.toString())

        adapter = HotEventsAdapter(hotEventList)
        binding.recyclerHotEvents.adapter = adapter
    }


    private fun fetchEvents() {
        Log.d("Main.Activity.kt", "FetchEvents: inside fetch in main")
        FirebaseFirestore.getInstance().collection("events")
            .get().addOnSuccessListener { result ->
                hotEventList.clear()
                Log.d("Main.Activity.kt", "Call to Database: ${result}")
                for (doc in result) {
                    val data = doc.data
                    val isHot = when (data["isHot"]) {
                        is Boolean -> data["isHot"] as Boolean
                        is String -> (data["isHot"] as String).toBoolean()
                        else -> false
                    }
                    val event = doc.toObject(Event::class.java)
                    Log.d("Main.Activity.kt", "Call to Database: $event")
                    Log.d("EventCheck", "Event title: ${event.title}, isHot: ${event.isHot}")
                    if (isHot) {
                        Log.d("Main.Activity.kt", "If is HOT: Event added to HOT list")
                        hotEventList.add(event)
                    }
                }
                adapter.notifyDataSetChanged() // IMPORTANT
            }
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // Already on Home
                R.id.nav_events -> {
                    startActivity(Intent(this, EventListActivity::class.java))
                    true
                }
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
    }

    override fun onPause() {
        super.onPause()
        imageHandler.removeCallbacks(imageRunnable)
    }
}
