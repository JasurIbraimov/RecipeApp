package com.jasur.recipeapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jasur.recipeapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val getStartedBtn: Button = findViewById(R.id.get_started_btn)
        getStartedBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java);
            startActivity(intent)
            finish()
        }

    }

}