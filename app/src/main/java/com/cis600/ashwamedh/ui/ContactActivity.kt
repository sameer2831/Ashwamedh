package com.cis600.ashwamedh.ui
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.databinding.ActivityContactBinding


class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Setup Bottom Navigation
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
                R.id.nav_contact -> {
                    // Already here
                    true
                }
                else -> false
            }
        }
        // You can set coordinator names, emails, or add WhatsApp intents here
        binding.tvSupportInfo.text = "For help, contact:\nJohn Doe - +91 9876543210\njohndoe@example.com"
    }
}