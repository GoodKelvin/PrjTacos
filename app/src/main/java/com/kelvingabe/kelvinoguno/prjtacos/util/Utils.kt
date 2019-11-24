package com.kelvingabe.kelvinoguno.prjtacos.util

import android.content.Context
import android.widget.EditText

class Utils(context: Context) {

    fun isEditTextEmpty(editText: EditText, errorString: String): Boolean {
        var text = editText.text.toString().trim()
        return if (text.isNullOrBlank()) {
            editText.error = errorString
            true
        } else false
    }
}