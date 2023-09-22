package com.example.contacts.RecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView;
import com.example.contacts.DATABASE.Model
import com.example.contacts.R
import java.util.zip.Inflater

class CustomAdapter(private val contactModelArrayList: ArrayList<Model>, private val context: Context) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    class ViewHolder(view : View)  : RecyclerView.ViewHolder(view){

        val contactName: TextView
        val contactNumber: TextView
            init {
                contactName = view.findViewById(R.id.tv_ContactName)
                contactNumber = view.findViewById(R.id.tv_number)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = contactModelArrayList[position]
        holder.contactName.text = model.contactName
        holder.contactNumber.text = model.contactNumber
    }

    override fun getItemCount(): Int {
        return contactModelArrayList.size
    }

}