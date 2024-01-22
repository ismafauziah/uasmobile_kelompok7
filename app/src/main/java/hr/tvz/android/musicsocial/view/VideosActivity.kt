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
import hr.tvz.android.musicsocial.adapters.VideoListAdapter
import hr.tvz.android.musicsocial.databinding.ActivityVideosBinding
import hr.tvz.android.musicsocial.viewmodel.VideoViewModel

class VideosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideosBinding
    private lateinit var viewModel: VideoViewModel
    private val videoListAdapter = VideoListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(VideoViewModel::class.java)
        viewModel.refresh()

        binding.videosList.apply {
            layoutManager = LinearLayoutManager(this@VideosActivity)
            adapter = videoListAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.videos.observe(this, Observer { videosList ->
            videosList?.let {
                videoListAdapter.updateVideosList(videosList)
            }
        })
    }

}