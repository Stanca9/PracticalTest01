package com.example.PracticalTest01

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.sqrt

class Colocviul_1Service : Service() {

    private val handler = Handler()
    private var num = 0.0

    // Creăm un Runnable pentru a trimite mesajul difuzat la fiecare 10 secunde
    private val broadcastRunnable = object : Runnable {
        override fun run() {
            val intent = Intent("com.example.myapplication.ACTION_SEND_DATA")

            // Obține data și ora actuală
            val currentDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

            // Adaugă datele în intenție
            intent.putExtra("datetime", currentDateTime)
            intent.putExtra("num", num)

            // Trimite mesajul difuzat
            sendBroadcast(intent)

            handler.postDelayed(this, 5000)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Preia numerele primite prin intenție
        num = intent?.getDoubleExtra("num", 0.0) ?: 0.0

        // Pornește difuzarea repetitivă la fiecare 10 secunde
        handler.post(broadcastRunnable)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Oprește difuzarea atunci când serviciul este distrus
        handler.removeCallbacks(broadcastRunnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Serviciul este de tip started, deci nu este legat de activitate
        return null
    }
}

