package com.cis600.ashwamedh.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cis600.ashwamedh.databinding.ActivityRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventName = intent.getStringExtra("eventName") ?: ""

        binding.submitRegistration.setOnClickListener {
            val reg = hashMapOf(
                "name" to binding.inputName.text.toString(),
                "email" to binding.inputEmail.text.toString(),
                "phone" to binding.inputPhone.text.toString(),
                "college" to binding.inputCollege.text.toString(),
                "eventName" to eventName
            )

            FirebaseFirestore.getInstance().collection("registrations")
                .add(reg)
                .addOnSuccessListener {
                    Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
