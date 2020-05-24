package com.yunchen.m07

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.download_status_item.view.*

class DownloadStatusAdapter(private val context: Context) :
    RecyclerView.Adapter<DownloadStatusAdapter.DownloadStatusViewHolder>() {

    private var downloadStatusList: List<DownloadStatusViewModel> = emptyList()

    class DownloadStatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int, data: DownloadStatusViewModel) {

            itemView.position.text = (position + 1).toString()

            itemView.url.text = data.url

            itemView.status.text = data.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadStatusViewHolder {
        val itemView = View.inflate(
            context,
            R.layout.download_status_item, null
        )

        return DownloadStatusViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return downloadStatusList.size
    }

    override fun onBindViewHolder(holder: DownloadStatusViewHolder, position: Int) {
        return holder.bindData(position, downloadStatusList[position])
    }

    fun updateDownloadStatusList(list: List<DownloadStatusViewModel>) {
        this.downloadStatusList = list

        notifyDataSetChanged()
    }

    fun updateDownloadStatus(url: String, status: String) {
        val data = downloadStatusList.firstOrNull { x -> x.url == url }?.let {
            it.status = status
        }

        notifyDataSetChanged()
    }
}