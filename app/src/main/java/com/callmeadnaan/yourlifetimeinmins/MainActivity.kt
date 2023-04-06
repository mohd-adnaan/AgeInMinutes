package com.callmeadnaan.yourlifetimeinmins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import java.util.Calendar
import android.app.DatePickerDialog
import java.util.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button =  findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectdDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker()
    {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayofMonth ->

                Toast.makeText(this,
                    "Year was $selectedYear,month was ${selectedMonth+1}"+
                            ", day of month was $selectedDayofMonth",
                    Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayofMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes =theDate.time / 60000  //div by 60000 bcoz its in lillisec

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time /60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()


            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}