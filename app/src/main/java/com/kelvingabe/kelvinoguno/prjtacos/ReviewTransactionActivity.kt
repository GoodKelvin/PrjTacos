package com.kelvingabe.kelvinoguno.prjtacos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kelvingabe.kelvinoguno.prjtacos.model.Transaction
import kotlinx.android.synthetic.main.activity_review_transaction.*
import kotlinx.android.synthetic.main.content_review_transaction.*

class ReviewTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_transaction)
        setSupportActionBar(toolbar)

        /* fab.setOnClickListener { view ->
             Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                     .setAction("Action", null).show()
         }*/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostResume() {
        super.onPostResume()
        unMarshalData()
    }

    fun unMarshalData() {
        review_transaction_recipient_textView.text = Transaction.receiver_name
        review_transaction_receive_textView.text = Transaction.receive_amount
        review_transaction_send_textView.text = Transaction.send_amount
    }
}
