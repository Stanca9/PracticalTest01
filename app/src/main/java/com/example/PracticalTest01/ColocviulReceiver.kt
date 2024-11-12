package com.example.PracticalTest01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ColocviulReceiver : BroadcastReceiver() {

    // Eticheta pentru jurnalizare
    private val TAG = "ColocviulReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        // Extragem datele primite prin mesajul difuzat
        val datetime = intent.getStringExtra("datetime")
        val num = intent.getDoubleExtra("num", 0.0)

        // Jurnalizăm mesajul cu detaliile primite
        Log.d(TAG, "Data și ora: $datetime")
        Log.d(TAG, "Numărul primit: $num")
    }
}
