package com.agecalculator

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvResult: TextView? = null
    private var tvResultInHours : TextView? = null
    private var tvResultInDays : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val datePicker: Button = findViewById(R.id.dateButton)

        datePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        tvSelectedDate = findViewById(R.id.selectedDate)
         tvResult = findViewById(R.id.result)
        tvResultInHours = findViewById(R.id.resultInHours)
        tvResultInDays = findViewById(R.id.resultInDays)

        //Date Picker Dialog
        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth->

                Toast.makeText(this,"You Selected $selectedDayOfMonth/${selectedMonth+1}/$selectedYear",Toast.LENGTH_SHORT).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = simpleDateFormat.parse(selectedDate)
                val selectedDay = theDate.day
                val currentDay = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val selectedDateInMinutes = theDate.time / 60000
                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time/60000
                val differenceInMinute = currentDateInMinutes-selectedDateInMinutes
                val differenceInHours = differenceInMinute/60
                val differenceInDays = differenceInHours/24
                tvResult?.text= differenceInMinute.toString()
                tvResultInHours?.text = differenceInHours.toString()
                tvResultInDays?.text = differenceInDays.toString()


            },
        year,
        month,
        day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }


}