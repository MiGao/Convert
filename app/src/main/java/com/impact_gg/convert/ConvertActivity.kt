package com.impact_gg.convert

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_convert.*
import java.text.DecimalFormat

class ConvertActivity : AppCompatActivity() {

    val decimalFormat: DecimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        fahrenheit.setOnEditorActionListener() { v, actionId, event ->
            if (actionId == 1337) {
                btnFtoC.performClick()
                true
            } else {
                false
            }
        }

        celsius.setOnEditorActionListener() { v, actionId, event ->
            if (actionId == 1338) {
                btnCtoF.performClick()
                true
            } else {
                false
            }
        }

        btnFtoC.setOnClickListener {
            val fahrenheitText = fahrenheit.text

            if (fahrenheitText.isNullOrEmpty()) return@setOnClickListener

            try {
                fahrenheitText.toString().toDouble()
            } catch (e: Exception) {
                return@setOnClickListener
            }

            celsius.setText(
                    fahrenheitToCelsius(fahrenheitText.toString().toDouble()))
        }

        btnCtoF.setOnClickListener {
            val celsiusText = celsius.text

            if (celsiusText.isNullOrEmpty()) return@setOnClickListener

            try {
                celsiusText.toString().toDouble()
            } catch (e: Exception) {
                return@setOnClickListener
            }

            fahrenheit.setText(
                    celsiusToFahrenheit(celsiusText.toString().toDouble())
            )
        }
        /*setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_convert, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun fahrenheitToCelsius(fahrenheit: Double): String {
        if (fahrenheit == null) return ""

        return decimalFormat.format(((fahrenheit - 32) * 5) / 9)
    }

    fun celsiusToFahrenheit(celsius: Double): String {
        if (celsius == null) return ""

        return decimalFormat.format(((celsius * 9) / 5) + 32)
    }
}
