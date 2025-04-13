package com.cis600.ashwamedh.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cis600.ashwamedh.databinding.ActivityContactBinding


class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // You can set coordinator names, emails, or add WhatsApp intents here
        binding.tvSupportInfo.text = "For help, contact:\nJohn Doe - +91 9876543210\njohndoe@example.com"
    }
}