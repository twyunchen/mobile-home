package com.yunchen.m05homework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yunchen.m05homework.dtos.GetPersonalizedPlayListResult
import com.yunchen.m05homework.http.HttpRequest
import com.yunchen.m05homework.http.PlayListService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var viewAdapter: PlayListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }


    private fun initRecyclerView() {
        viewManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        viewAdapter = PlayListAdapter(this)

        initPlayList()

        playList.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    private fun initPlayList() {
        val call = HttpRequest().makeService<PlayListService>().getPersonalizedPlayList()

        call.enqueue(object : Callback<GetPersonalizedPlayListResult> {

            override fun onFailure(call: Call<GetPersonalizedPlayListResult>?, t: Throwable?) {
                Log.w("api", "call api failed" + t.toString())
            }

            override fun onResponse(
                call: Call<GetPersonalizedPlayListResult>,
                response: Response<GetPersonalizedPlayListResult>
            ) {
                val playListData = response.body()!!.result

                viewAdapter.setPlayListData(playListData)
            }
        })
    }
}
