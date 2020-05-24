package com.yunchen.m07

import androidx.lifecycle.ViewModel

data class DownloadStatusViewModel(val url: String, var status: String) : ViewModel() {

}