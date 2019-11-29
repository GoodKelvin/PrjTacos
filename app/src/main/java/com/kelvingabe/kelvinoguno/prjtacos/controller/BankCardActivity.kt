package com.kelvingabe.kelvinoguno.prjtacos.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kelvingabe.kelvinoguno.prjtacos.R
import kotlinx.android.synthetic.main.activity_bank_card.*

class BankCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_card)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
