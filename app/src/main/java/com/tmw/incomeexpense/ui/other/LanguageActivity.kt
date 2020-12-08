package com.tmw.incomeexpense.ui.other

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.tmw.incomeexpense.LocaleManager
import com.tmw.incomeexpense.MainActivity
import com.tmw.incomeexpense.R
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*

class LanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        eng_lang.setOnClickListener {
            LocaleManager.setNewLocale(this,"en")
            recreate()
        }

        myan_lang.setOnClickListener {
            LocaleManager.setNewLocale(this,"my")
            recreate()
        }

        btn_change.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

}