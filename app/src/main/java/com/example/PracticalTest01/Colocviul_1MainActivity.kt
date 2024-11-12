package com.example.PracticalTest01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Colocviul_1MainActivity : AppCompatActivity() {
    private var nrClicks = 0
    private var isServiceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colocviul1_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navButton = findViewById<Button>(R.id.nav_button)
        val lText = findViewById<TextView>(R.id.l_text)
        val rText = findViewById<TextView>(R.id.r_text)
        val lButton = findViewById<Button>(R.id.l_button)
        val rButton = findViewById<Button>(R.id.r_button)

        navButton.text = getString(R.string.nav_button)
        lText.text = getString(R.string.l_text)
        rText.text = getString(R.string.r_text)
        lButton.text = getString(R.string.l_button)
        rButton.text = getString(R.string.r_button)

        lButton.setOnClickListener {
            lText.text = (lText.text.toString().toInt() + 1).toString()
            nrClicks++
        }

        rButton.setOnClickListener {
            rText.text = (rText.text.toString().toInt() + 1).toString()
            nrClicks++
        }

        if(nrClicks == 4){
            val intent = Intent(this, Colocviul_1Service::class.java).apply {
                putExtra("num", nrClicks)
            }
            startService(intent)
            isServiceRunning = true
        }

        navButton.setOnClickListener{
            val intent = Intent(this, Colocviul_1SecondaryActivity::class.java)
            intent.putExtra("total_clicks", nrClicks)
            startActivityForResult(intent, 1)
            nrClicks = 0
            lText.text = getString(R.string.l_text)
            rText.text = getString(R.string.r_text)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val lText = findViewById<TextView>(R.id.l_text)
        val rText = findViewById<TextView>(R.id.r_text)

        super.onSaveInstanceState(outState)
        outState.putString("lTextValue", lText.text.toString())
        outState.putString("rTextValue", rText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val lText = findViewById<TextView>(R.id.l_text)
        val rText = findViewById<TextView>(R.id.r_text)

        super.onRestoreInstanceState(savedInstanceState)
        lText.text = savedInstanceState.getString("lTextValue")
        rText.text = savedInstanceState.getString("rTextValue")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val message = when (resultCode) {
                Activity.RESULT_OK -> "Rezultat: Register"
                Activity.RESULT_CANCELED -> "Rezultat: Cancel"
                else -> "Rezultat necunoscut"
            }
            // Afișează rezultatul într-un Toast
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Oprește serviciul dacă este în execuție când activitatea este distrusă
        if (isServiceRunning) {
            val intent = Intent(this, Colocviul_1Service::class.java)
            stopService(intent)
            Toast.makeText(this, "Serviciul a fost oprit", Toast.LENGTH_SHORT).show()
        }
    }
}