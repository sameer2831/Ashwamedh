package com.cis600.ashwamedh.model

data class Contact(
    val name: String,
    val phone: String,
    val email: String,
    val imageResId: String // resource id of drawable for contact image
)