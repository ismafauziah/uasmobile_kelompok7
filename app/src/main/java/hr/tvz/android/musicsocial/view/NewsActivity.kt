package hr.tvz.android.musicsocial.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.adapters.NewsListAdapter
import hr.tvz.android.musicsocial.databinding.ActivityNewsBinding
import hr.tvz.android.musicsocial.viewmodel.NewsViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var viewModel: NewsViewModel
    private val newsListAdapter = NewsListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.refresh()

        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@NewsActivity)
            adapter = newsListAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.news.observe(this, Observer { newsList ->
            newsList?.let {
                newsListAdapter.updateNewsList(newsList)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.news_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuItemAddNews -> {
                startActivity(Intent(this, NewsAddActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}