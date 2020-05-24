package com.yunchen.m07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

const val WAIT_DOWNLOAD = "等待下载"
const val COMPLETE_DOWNLOAD = "等待下载"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: DownloadStatusAdapter

    private val downloadStatusList: List<DownloadStatusViewModel> = listOf(
        DownloadStatusViewModel("http://tw-mobiel-training/1.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/2.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/3.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/4.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/5.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/6.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/7.jpg", WAIT_DOWNLOAD),
        DownloadStatusViewModel("http://tw-mobiel-training/8.jpg", WAIT_DOWNLOAD)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewAdapter = DownloadStatusAdapter(this)

        viewAdapter.updateDownloadStatusList(downloadStatusList)

        recyclerView =  downloadStatus.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }




    }
}
