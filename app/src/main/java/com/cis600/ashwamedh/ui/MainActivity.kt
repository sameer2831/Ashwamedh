package com.cis600.ashwamedh.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.adapter.ImageSliderAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var imageSlider: ViewPager2
    private lateinit var imageHandler: Handler
    private lateinit var imageRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Use layout directly (no view binding here)

        val imageList = listOf(
            R.drawable.placeholder,
            R.drawable.placeholder,
            R.drawable.placeholder
        )

        imageSlider = findViewById(R.id.imageSlider)
        imageSlider.adapter = ImageSliderAdapter(imageList)

        imageHandler = Handler(Looper.getMainLooper())
        imageRunnable = Runnable {
            imageSlider.currentItem = (imageSlider.currentItem + 1) % imageList.size
        }

        imageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                imageHandler.removeCallbacks(imageRunnable)
                imageHandler.postDelayed(imageRunnable, 3000)
            }
        })

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
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
