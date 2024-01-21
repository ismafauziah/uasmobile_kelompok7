package hr.tvz.android.musicsocial.adapters

import android.content.ActivityNotFoundException
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.helper.Toast
import hr.tvz.android.musicsocial.model.Video

class VideoListAdapter(private val videosList: ArrayList<Video>): RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    class VideoViewHolder(view: View, videosList: ArrayList<Video>): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.videoTitle)

        init {
            view.setOnClickListener {
                val connManager: ConnectivityManager = view.context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.isConnectedOrConnecting
                val mobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.isConnectedOrConnecting

                if(!wifi!! && !mobile!!) {
                    Toast.createErrorToast(view.context.applicationContext, view.context.getString(R.string.toast_video_no_internet))
                } else {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videosList[adapterPosition].link))

                    try {
                        view.context.startActivity(intent)
                    } catch (error: ActivityNotFoundException) {
                        Toast.createErrorToast(view.context.applicationContext, view.context.getString(R.string.toast_video_error))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view, videosList)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.title.text = videosList[position].title
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    fun updateVideosList(newUpdatedVideos: List<Video>) {
        videosList.clear()
        videosList.addAll(newUpdatedVideos)
        notifyDataSetChanged()
    }

}