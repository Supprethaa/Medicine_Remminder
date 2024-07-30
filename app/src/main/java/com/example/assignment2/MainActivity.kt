package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class MainActivity : AppCompatActivity(), AddReminderDialogFragment.AddReminderListener {

    private lateinit var remindersRecyclerView: RecyclerView
    private lateinit var noRemindersTextView: TextView
    private val reminders = mutableListOf<Reminder>()
    private lateinit var adapter: ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Medicine Reminder"

        remindersRecyclerView = findViewById(R.id.remindersRecyclerView)
        noRemindersTextView = findViewById(R.id.noRemindersTextView)

        adapter = ReminderAdapter(reminders)
        remindersRecyclerView.layoutManager = LinearLayoutManager(this)
        remindersRecyclerView.adapter = adapter

        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                showAddReminderDialog()
                true
            }
            R.id.item2 -> {
                // Navigate to CurrentMedicationActivity
                val intent = Intent(this, CurrentMedicationActivity::class.java)
                startActivity(intent)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAddReminderDialog() {
        val dialogFragment = AddReminderDialogFragment()
        dialogFragment.show(supportFragmentManager, "AddReminderDialog")
    }

    override fun onAddReminder(reminder: Reminder) {
        reminders.add(0, reminder) // Add to the top of the list
        updateUI()
    }

    private fun updateUI() {
        if (reminders.isEmpty()) {
            remindersRecyclerView.visibility = View.GONE
            noRemindersTextView.visibility = View.VISIBLE
        } else {
            remindersRecyclerView.visibility = View.VISIBLE
            noRemindersTextView.visibility = View.GONE
            adapter.notifyDataSetChanged()
        }
    }
}