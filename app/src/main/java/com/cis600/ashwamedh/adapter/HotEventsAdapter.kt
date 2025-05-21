package com.cis600.ashwamedh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.model.Event

class HotEventsAdapter(
    private val events: List<Event>
) : RecyclerView.Adapter<HotEventsAdapter.HotEventViewHolder>() {

    inner class HotEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
        val eventName: TextView = itemView.findViewById(R.id.hotEventTitle)
        val eventDate: TextView = itemView.findViewById(R.id.hotEventDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotEventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hot_event, parent, false)
        return HotEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotEventViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.title

        // Format date if needed
        holder.eventDate.text = event.date ?: "TBD"

        // Load image with Glide safely (handle null or empty URLs)
        Glide.with(holder.itemView.context)
            .load(event.imageURL)
            .placeholder(R.drawable.placeholder) // Optional: fallback image
            //.error(R.drawable.error_image) // Optional: error image
            .into(holder.eventImage)
    }

    override fun getItemCount(): Int = events.size
}
