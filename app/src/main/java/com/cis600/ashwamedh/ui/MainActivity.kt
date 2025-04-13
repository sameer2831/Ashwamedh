package com.cis600.ashwamedh.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cis600.ashwamedh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEvents.setOnClickListener {
            startActivity(Intent(this, EventListActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.btnContact.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }
    }
}
