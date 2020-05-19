package com.yunchen.m05homework

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yunchen.m05homework.dtos.PersonalizedResult

class PlayListAdapter(private val context: Context) :
    RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {

    private var playList: List<PersonalizedResult> = ArrayList()

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class PlayListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val playListName: TextView = itemView.findViewById(R.id.playListName)

        val playListCover: ImageView = itemView.findViewById(R.id.playListCover)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayListViewHolder {

        val itemView = View.inflate(context, R.layout.play_list, null)

        return PlayListViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        val data = playList[position]

        holder.playListName.text = data.name

        Glide
            .with(context)
            .load(data.picUrl+ "?param=200y200")
            .placeholder(R.drawable.cover)
            .into(holder.playListCover)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = playList.size

    fun setPlayListData(playList: List<PersonalizedResult>) {
        this.playList = ArrayList(playList)

        notifyDataSetChanged()
    }
}