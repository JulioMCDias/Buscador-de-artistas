package com.jlmcdeveloper.buscadordeartistas.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val date: String, val listener: (date: String) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year: Int?
        val month: Int?
        val day: Int?

        if(!date.isEmpty()) {
            val dateParts = date.split('/')
            day = Integer.parseInt(dateParts[0])
            month = Integer.parseInt(dateParts[1])-1
            year = Integer.parseInt(dateParts[2])
        }else {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
             year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)
            day = c.get(Calendar.DAY_OF_MONTH)
        }
        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener("%02d/%02d/%04d".format(day, month+1, year))
    }
}