package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.squareup.picasso.Picasso

class SearchResultRecyclerAdapter (val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<SearchResultRecyclerAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.search_result_card,parent,false)
        return  MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return dataList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData=dataList[position]
        holder.musicTitle.text = currentData.title
        Picasso.get().load(currentData.album.cover).into(holder.musicImage)

        var player = MediaPlayer.create(context,currentData.preview.toUri())

        holder.musicPlayBtn.setOnClickListener{

            player.start()
            // play song , hide play btn and show pause and stop btn

            holder.musicPauseBtn.visibility = View.VISIBLE
            holder.musicStopBtn.visibility = View.VISIBLE
            holder.musicPlayBtn.visibility = View.GONE
        }
        holder.musicPauseBtn.setOnClickListener{
            player.pause()
            holder.musicPauseBtn.visibility = View.GONE
            holder.musicStopBtn.visibility = View.GONE
            holder.musicPlayBtn.visibility = View.VISIBLE
            // pause song , hide pause btn and stop btn , show play btn
        }
        holder.musicStopBtn.setOnClickListener{
            player.stop()
            holder.musicPauseBtn.visibility = View.GONE
            holder.musicStopBtn.visibility = View.GONE
            holder.musicPlayBtn.visibility = View.VISIBLE
            player = MediaPlayer.create(context,currentData.preview.toUri())
            // stop song , hide pause btn and stop btn , show play btn
        }
    }



    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val musicImage : ImageView
        val musicTitle: TextView
        val musicPlayBtn: ImageButton
        val musicPauseBtn: ImageButton
        val musicStopBtn: ImageButton

        init {
            musicImage= itemView.findViewById(R.id.musicImage)
            musicTitle= itemView.findViewById(R.id.musicTitle)
            musicPlayBtn= itemView.findViewById(R.id.musicPlayBtn)
            musicPauseBtn= itemView.findViewById(R.id.musicPauseBtn)
            musicStopBtn= itemView.findViewById(R.id.musicStopBtn)

        }
    }
}