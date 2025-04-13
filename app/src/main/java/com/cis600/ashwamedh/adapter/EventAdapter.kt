package com.cis600.ashwamedh.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cis600.ashwamedh.ui.EventDetailActivity
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.model.Event

class EventAdapter(
    private val context: Context,
    private val eventList: List<Event>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventImage: ImageView = itemView.findViewById(R.id.eventImage)
        val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        val eventCategory: TextView = itemView.findViewById(R.id.eventCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.eventTitle.text = event.title
        holder.eventCategory.text = event.category

        Glide.with(context)
            .load(event.imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(holder.eventImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, EventDetailActivity::class.java)
            intent.putExtra("event", event) // Make sure EventModel is Serializable or Parcelable
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = eventList.size
}
