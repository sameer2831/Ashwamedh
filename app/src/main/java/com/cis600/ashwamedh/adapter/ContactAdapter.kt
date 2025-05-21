package com.cis600.ashwamedh.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cis600.ashwamedh.R
import com.cis600.ashwamedh.model.Contact

class ContactAdapter(
    private val contacts: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.contactImage)
        val nameTextView: TextView = itemView.findViewById(R.id.contactName)
        val phoneTextView: TextView = itemView.findViewById(R.id.contactPhone)
        val emailTextView: TextView = itemView.findViewById(R.id.contactEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.phoneTextView.text = contact.phone
        holder.emailTextView.text = contact.email

        // If your Contact model has an image resource ID, set it; else set default or hide
        holder.imageView.setImageResource(R.drawable.placeholder)
    }

    override fun getItemCount(): Int = contacts.size
}
