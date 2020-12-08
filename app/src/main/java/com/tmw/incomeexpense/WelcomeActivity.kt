package com.tmw.incomeexpense

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class WelcomeActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        val handler=Handler()
        handler.postDelayed(Runnable () {
            val intent= Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        },1000)
    }
}