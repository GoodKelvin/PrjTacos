package com.kelvingabe.kelvinoguno.prjtacos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
object Transaction : Parcelable {
    var transaction_id: String? = null
    var send_amount: String? = null
    var receive_amount: String? = null
    var exchange_rate: String? = "353"
    var user_id: String? = null
    var user_name: String? = null
    var receiver_id: String? = null
    var receiver_name: String? = null
    var receiver_country: String? = "Nigeria"
    var receiver_bank: String? = null
    var receiver_account_number: String? = null
    var app_initiation_timestamp: String? = null
}