package com.yunchen.m09.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yunchen.m09.model.SlipResponse
import com.yunchen.m09.repository.SlipRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val slipRepository: SlipRepository) : ViewModel() {
    private val _advice: MutableLiveData<String> = MutableLiveData()
    val advice: LiveData<String> = _advice


    fun load() {
        slipRepository.getSlip().enqueue(object : Callback<SlipResponse> {

            override fun onFailure(call: Call<SlipResponse>, t: Throwable) {
                _advice.postValue("网络请求失败")
            }

            override fun onResponse(
                call: Call<SlipResponse>,
                response: Response<SlipResponse>
            ) {
                if (response.isSuccessful) {
                    _advice.postValue(response.body()!!.slip.advice)
                } else {
                    _advice.postValue("网络请求失败")
                }
            }

        })


    }
}
