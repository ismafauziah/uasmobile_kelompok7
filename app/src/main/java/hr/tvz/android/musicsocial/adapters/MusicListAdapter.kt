package hr.tvz.android.musicsocial.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.model.Music
import hr.tvz.android.musicsocial.view.MusicDetailsActivity

class MusicListAdapter(private val musicList: ArrayList<Music>): RecyclerView.Adapter<MusicListAdapter.MusicViewHolder>() {

    class MusicViewHolder(view: View, musicList: ArrayList<Music>): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.musicTitle)

        init {
            view.setOnClickListener {
                val intent = Intent(view.context.applicationContext, MusicDetailsActivity::class.java)
                intent.putExtra("music", musicList[adapterPosition])
                startActivity(view.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.music_item, parent, false)
        return MusicViewHolder(view, musicList)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.title.text = musicList[position].title
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    fun updateMusicList(newUpdatedMusic: List<Music>) {
        musicList.clear()
        musicList.addAll(newUpdatedMusic)
        notifyDataSetChanged()
    }

}