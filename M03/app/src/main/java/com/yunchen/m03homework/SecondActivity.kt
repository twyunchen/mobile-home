package com.yunchen.m03homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        send.setOnClickListener() {
            val result: String = editText.text.toString();

            val intent = Intent()
            intent.putExtra("text", result)

            setResult(2000, intent)
            finish()
        }
    }
}
