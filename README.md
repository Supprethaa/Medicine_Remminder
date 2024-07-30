# Medicine Reminder App

## Overview
The Medicine Reminder App is an Android application designed to help users manage their medication schedules. It allows users to add reminders for their medicines and view their current medication list.

## Features
- Add new medicine reminders
- View current medications
- User-friendly interface with a modern Material Design
- Easy-to-use menu options for quick navigation

## Technical Details
- Developed for Android using Kotlin
- Utilizes Android Jetpack components
- Implements RecyclerView for efficient list display
- Uses CardView for a visually appealing layout

## Project Structure
- `MainActivity`: The main entry point of the app, handling the primary UI and navigation.
- `AddReminderDialogFragment`: A dialog fragment for adding new medicine reminders.
- `CurrentMedicationActivity`: Displays the list of current medications.
- `ReminderAdapter`: RecyclerView adapter for displaying reminder items.
- `item_reminder.xml`: Layout file for individual reminder items.
- `current_medication.xml`: Layout file for the current medication list.

## Usage
1. Launch the app to view the main screen.
2. Use the menu options to:
   - Add a new reminder
   - View current medications
3. When adding a reminder, fill in the medicine name, disease, date, and time.
4. View your added reminders in the main screen's RecyclerView.
5. Access the current medication list through the menu option.

## Dependencies
- AndroidX libraries
- Material Design components
- CardView and RecyclerView for UI



