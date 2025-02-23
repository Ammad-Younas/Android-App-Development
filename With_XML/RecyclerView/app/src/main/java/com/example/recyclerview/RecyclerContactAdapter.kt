package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerContactAdapter(private val context: Context, private val arrContacts: ArrayList<ContactModel>) : RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val img: ImageView? = itemView.findViewById(R.id.avatar)
        val name: TextView? = itemView.findViewById(R.id.name)
        val email: TextView? = itemView.findViewById(R.id.email)
        val time: TextView? = itemView.findViewById(R.id.time)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_ui_for_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img?.setImageResource(arrContacts[position].img)
        holder.name?.text = arrContacts[position].name
        holder.email?.text = arrContacts[position].email
        holder.time?.text = arrContacts[position].time
    }

    override fun getItemCount(): Int {
        return arrContacts.size
    }

}