package com.example.assignment2

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class AddReminderDialogFragment: DialogFragment() {
    private lateinit var medicineNameEditText: EditText
    private lateinit var diseaseNameSpinner: Spinner
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText

    interface AddReminderListener {
        fun onAddReminder(reminder: Reminder)
    }

    private var listener: AddReminderListener? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.add_reminder_dialog_fragment, container, false)

        medicineNameEditText = view.findViewById(R.id.medicineName)
        diseaseNameSpinner = view.findViewById(R.id.diseaseSpinner)
        dateEditText = view.findViewById(R.id.date)
        timeEditText = view.findViewById(R.id.time)

        val addButton: Button = view.findViewById(R.id.addButton)
        val cancelButton: Button = view.findViewById(R.id.cancelButton)

        // Set up disease name spinner
        val diseases = arrayOf("Fever", "Headache", "Cold", "Allergy", "Other")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, diseases)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diseaseNameSpinner.adapter = adapter

        // Set up date picker
        dateEditText.setOnClickListener {
            showDatePicker()
        }

        // Set up time picker
        timeEditText.setOnClickListener {
            showTimePicker()
        }

        addButton.setOnClickListener {
            addReminder()
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

        return view


    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
            val selectedDate = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year)
            dateEditText.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
            val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            timeEditText.setText(selectedTime)
        }, hour, minute, false)

        timePickerDialog.show()
    }

    private fun addReminder() {
        val medicineName = medicineNameEditText.text.toString()
        val diseaseName = diseaseNameSpinner.selectedItem.toString()
        val date = dateEditText.text.toString()
        val time = timeEditText.text.toString()

        if (medicineName.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
            val newReminder = Reminder(medicineName, diseaseName, date, time)
            listener?.onAddReminder(newReminder)
            dismiss()
        } else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddReminderListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement AddReminderListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}

