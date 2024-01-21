package hr.tvz.android.musicsocial.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.model.News
import hr.tvz.android.musicsocial.view.NewsDetailsActivity

class NewsListAdapter(private val newsList: ArrayList<News>): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View, newsList: ArrayList<News>): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.newsTitle)

        init {
            view.setOnClickListener {
                val intent = Intent(view.context.applicationContext, NewsDetailsActivity::class.java)
                intent.putExtra("news", newsList[adapterPosition])
                startActivity(view.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view, newsList)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.text = newsList[position].title
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNewsList(newUpdatedNews: List<News>) {
        newsList.clear()
        newsList.addAll(newUpdatedNews)
        notifyDataSetChanged()
    }

}

