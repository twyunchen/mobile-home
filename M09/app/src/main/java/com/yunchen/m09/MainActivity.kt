package com.yunchen.m09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.yunchen.m09.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refresh.setOnClickListener {
            refreshData()
        }

        mainActivityViewModel.advice.observe(this, Observer {
            changeAdvice(it)
        })
    }

    private fun refreshData() {
        mainActivityViewModel.load();
    }

    fun changeAdvice(adviceText: String) {
        advice.text = adviceText
    }
}
