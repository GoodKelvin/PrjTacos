package com.kelvingabe.kelvinoguno.prjtacos.controller

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kelvingabe.kelvinoguno.prjtacos.R

import kotlinx.android.synthetic.main.activity_personal_info.*

class PersonalInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onSaveClicked(view: View) {}
    fun onCancelClicked(view: View) {
        finish()
    }

}
