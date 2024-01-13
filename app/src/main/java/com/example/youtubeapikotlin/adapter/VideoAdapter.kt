package com.example.youtubeapikotlin.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapikotlin.model.VideoYtModel

class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var oldItems = emptyList<VideoYtModel>()

    class VideoHolder(itemView: itemVideoBinding) : RecyclerView.ViewHolder(itemView.root)
        private val binding = itemView

    fun setData(data: VideoYtModel.VideoItem){
        binding.tvVideoTitle.text = data.snippetYt.title
        binding.tvPublished.text = data.snippetYt.publishedAt
        Glide.with(binding.root)
            .load(data.snippetYt.thumbnails.high.url)
            .into(binding.ivThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoHolder(view)
    }

    override fun getItemCount(): Int {
        return oldItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return oldItems.size
    }

    fun setData(newList: List<VideoYtModel.VideoItem>){
        val videoDiff = VideoDiffUtil(oldItems, newList)
        val diff = DiffUtil.calculateDiff(videoDiff)
        oldItems = newList
        diff.dispatchUpdatesTo(this)
    }

}