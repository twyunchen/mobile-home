package com.yunchen.m07

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

const val WAIT_DOWNLOAD = "等待下载"
const val COMPLETE_DOWNLOAD = "下载完成"

const val DOWNLOAD_MSG = 1

class MainActivity : AppCompatActivity() {
    private lateinit var mainHandler: Handler
    private lateinit var downloadHandler: Handler
    private lateinit var handlerThread: HandlerThread
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

        mainHandler = Handler(Looper.getMainLooper())

        initDownload()

        start.setOnClickListener {
            startDownload()
        }

        stop.setOnClickListener {
            if (handlerThread.isAlive) {
                handlerThread.quit()
            }
        }

        restart.setOnClickListener {
            if (!handlerThread.isAlive) {
                initDownload()
            }
            startDownload()
        }
    }

    private fun startDownload() {
        downloadStatusList.forEach {
            val message = Message()
            message.what = DOWNLOAD_MSG
            message.obj = it
            downloadHandler.sendMessage(message)
        }
    }

    override fun onStart() {
        super.onStart()

        val viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewAdapter = DownloadStatusAdapter(this)

        viewAdapter.updateDownloadStatusList(downloadStatusList)

        recyclerView = downloadStatus.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (handlerThread.isAlive) {
            handlerThread.quitSafely()
        }
    }

    private fun initDownload() {
        handlerThread = HandlerThread("DownloadThread")

        handlerThread.start()

        downloadHandler = object : Handler(handlerThread.looper) {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                when (msg.what) {
                    DOWNLOAD_MSG -> {
                        val model = msg.obj as DownloadStatusViewModel

                        Thread.sleep(1000)

                        model.status = COMPLETE_DOWNLOAD

                        mainHandler.post {
                            viewAdapter.updateDownloadStatus(model.url, model.status)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}




