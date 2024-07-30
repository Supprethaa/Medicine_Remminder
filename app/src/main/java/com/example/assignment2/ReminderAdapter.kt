package com.example.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Reminder(
    val medicineName: String,
    val diseaseName: String,
    val date: String,
    val time: String
)


class ReminderAdapter(private val reminders: List<Reminder>) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val medicineNameTextView: TextView = view.findViewById(R.id.medicineName)
        val diseaseNameTextView: TextView = view.findViewById(R.id.diseaseName)
        val reminderDateTextView: TextView = view.findViewById(R.id.reminderDate)
        val reminderTimeTextView: TextView = view.findViewById(R.id.reminderTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reminder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.medicineNameTextView.text = reminder.medicineName
        holder.diseaseNameTextView.text = reminder.diseaseName
        holder.reminderDateTextView.text = "Date: ${reminder.date}"
        holder.reminderTimeTextView.text = "Time: ${reminder.time}"
    }

    override fun getItemCount() = reminders.size
}