package hr.tvz.android.musicsocial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hr.tvz.android.musicsocial.adapters.MusicListAdapter
import hr.tvz.android.musicsocial.databinding.ActivityMusicBinding
import hr.tvz.android.musicsocial.viewmodel.MusicViewModel

class MusicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicBinding
    private lateinit var viewModel: MusicViewModel
    private val musicListAdapter = MusicListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MusicViewModel::class.java)
        viewModel.refresh()

        binding.musicList.apply {
            layoutManager = LinearLayoutManager(this@MusicActivity)
            adapter = musicListAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        MusicViewModel.music.observe(this, Observer { musicList ->
            musicList?.let {
                musicListAdapter.updateMusicList(musicList)
            }
        })
    }

}