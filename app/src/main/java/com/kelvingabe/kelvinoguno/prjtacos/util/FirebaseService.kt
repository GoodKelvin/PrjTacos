package com.kelvingabe.kelvinoguno.prjtacos.util

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.kelvingabe.kelvinoguno.prjtacos.model.App
import java.util.*
import kotlin.collections.HashMap

class FirebaseService(context: Context) {
    val TAG = "Firebase Service"

    fun firestoreSaveUser(user_id: String, email: String) {
        val user = HashMap<String, Any>()
        user.put("userId", user_id)
        user.put("email", email)
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(user_id)
            .set(user)
            .addOnSuccessListener(object : OnSuccessListener<Void> {
                override fun onSuccess(aVoid: Void) {
                    Log.e(TAG, "DocumentSnapshot successfully written!")
                }
            })
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Log.e(TAG, "Error writing document", e)
                }
            })
    }

    fun firestoreSaveTransfer(
        account_name: String,
        account_number: String,
        bank_name: String,
        send_amt: String,
        receive_amt: String
    ) {
        val transferObject = HashMap<String, Any>()
        transferObject.put("account_name", account_name)
        transferObject.put("account_number", account_number)
        transferObject.put("bank_name", bank_name)
        transferObject.put("country_name", "Nigeria")
        transferObject.put("country_code3", "nga")
        transferObject.put("server_timestamp", FieldValue.serverTimestamp())
        transferObject.put("transfer_status", "complete")
        val date = Date()
        transferObject.put("device_timestamp", Timestamp(date))
        val key = account_name.replace(" ", "") + account_number
        transferObject.put("user_id", App.prefs.userID!!)
        transferObject.put("recipient_id", key)
        transferObject.put("send_amt", send_amt)
        transferObject.put("receive_amt", receive_amt)
        var time = date.getTime()
        var key2 = account_name.replace(" ", "") + time;
        val db = FirebaseFirestore.getInstance()
        val reference = db.collection("users").document(App.prefs.userID!!)
            .collection("transfers")//.document(key2)
        reference.add(transferObject)
            .addOnSuccessListener { Log.e(TAG, "Transfer successfully written!") }
            .addOnFailureListener { e -> Log.e(TAG, "Error writing transfer", e) }
    }
}