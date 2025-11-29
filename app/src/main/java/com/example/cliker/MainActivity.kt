package com.example.cliker

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    private lateinit var totalCounterText: TextView
    private lateinit var sessionCounterText: TextView

    private var totalCounter = 0
    private var sessionCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Скрываем ActionBar
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        totalCounterText = findViewById(R.id.totalCounter)
        sessionCounterText = findViewById(R.id.sessionCounter)

        // читаем сохранённый общий счетчик
        val prefs = getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        totalCounter = prefs.getInt("total_counter", 0)

        updateUi()
    }

    private fun updateUi() {
        totalCounterText.text = "Всего нажатий: $totalCounter"
        sessionCounterText.text = "За сессию: $sessionCounter"
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            totalCounter++
            sessionCounter++

            updateUi()

            // сохраняем общий счетчик
            val prefs = getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
            prefs.edit().putInt("total_counter", totalCounter).apply()
        }
        return super.onTouchEvent(event)
    }
}
