package com.cis600.ashwamedh.model

import java.io.Serializable
import java.net.URL

data class Event(
    val title: String = "",
    val category: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = "",
    val venue: String = "",
    val rules: String = "",
    val imageURL:  String= "",
    val isHot: Boolean = false
) : java.io.Serializable