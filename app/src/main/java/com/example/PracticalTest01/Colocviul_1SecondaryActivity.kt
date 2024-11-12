package com.example.PracticalTest01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Colocviul_1SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colocviul_1_secondary)

        // Preia numărul total de accesări din Intent
        val countTextView = findViewById<TextView>(R.id.textViewCount)
        val totalClicks = intent.getIntExtra("total_clicks", 0)
        countTextView.text = "Total apăsări: $totalClicks"

        // Buton Ok
        val okButton = findViewById<Button>(R.id.buttonOk)
        okButton.setOnClickListener {
            // Setează rezultatul și închide activitatea
            setResult(Activity.RESULT_OK)
            finish()
        }

        // Buton Cancel
        val cancelButton = findViewById<Button>(R.id.buttonCancel)
        cancelButton.setOnClickListener {
            // Setează rezultatul și închide activitatea
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
