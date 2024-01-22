package hr.tvz.android.musicsocial.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import com.gauravk.audiovisualizer.visualizer.BarVisualizer
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.databinding.ActivityMusicDetailsBinding
import hr.tvz.android.musicsocial.model.Music
import hr.tvz.android.musicsocial.viewmodel.MusicViewModel
import java.lang.Exception

class MusicDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicDetailsBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekbarUpdate: Thread
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var music: Music? = intent.extras!!.getParcelable("music")

        if(music != null) {
            binding.musicDetailsTitle.text = music.title
        }

        val songId = resources.getIdentifier(music?.fileName, "raw", packageName)
        mediaPlayer = MediaPlayer.create(this, songId)
        mediaPlayer.start()

        seekbarUpdate = Thread {
            val duration = mediaPlayer.duration
            var currentPosition = 0

            while (currentPosition < duration) {
                try {
                    Thread.sleep(500)
                    currentPosition = mediaPlayer.currentPosition
                    binding.musicDetailsSeekbar.progress = currentPosition
                } catch (error: Exception) {
                    error.printStackTrace()
                }
            }
        }
        binding.musicDetailsSeekbar.max = mediaPlayer.duration
        seekbarUpdate.start()
        binding.musicDetailsSeekbar.progressDrawable.setColorFilter(resources.getColor(R.color.blue), PorterDuff.Mode.MULTIPLY)
        binding.musicDetailsSeekbar.thumb.setColorFilter(resources.getColor(R.color.red_200), PorterDuff.Mode.SRC_IN)

        binding.musicDetailsSeekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) { }

            override fun onStartTrackingTouch(p0: SeekBar?) { }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                mediaPlayer.seekTo(p0!!.progress)
            }
        })

        binding.musicDetailsSeekbarEnd.text = convertTime(mediaPlayer.duration)
        handler = Handler()
        handler.post(object: Runnable {
            override fun run() {
                binding.musicDetailsSeekbarStart.text = convertTime(mediaPlayer.currentPosition)
                handler.postDelayed(this, 1000)
            }
        })

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.musicDetailsPlay.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                binding.musicDetailsPlay.setBackgroundResource(R.drawable.play_icon)
                mediaPlayer.pause()
            } else {
                binding.musicDetailsPlay.setBackgroundResource(R.drawable.pause_icon)
                mediaPlayer.start()
            }
        }

        binding.musicDetailsNext.setOnClickListener {
            resetMediaPlayer()

            var index = MusicViewModel.music.value?.indexOfFirst { song -> song.title == music?.title }
            index = if (index == MusicViewModel.music.value?.size?.minus(1)) 0 else (index!! + 1)

            val nextSong = MusicViewModel.music.value?.get(index)
            val nextSongId = resources.getIdentifier(nextSong?.fileName, "raw", packageName)
            mediaPlayer = MediaPlayer.create(this, nextSongId)
            binding.musicDetailsTitle.text = nextSong?.title
            mediaPlayer.start()
            startAnimation()
            binding.musicDetailsSeekbarEnd.text = convertTime(mediaPlayer.duration)

            music = nextSong
        }

        binding.musicDetailsPrevious.setOnClickListener {
            resetMediaPlayer()

            var index = MusicViewModel.music.value?.indexOfFirst { song -> song.title == music?.title }
            index = if (index == 0) MusicViewModel.music.value?.size?.minus(1) else (index!! - 1)

            val prevSong = MusicViewModel.music.value?.get(index!!)
            val prevSongId = resources.getIdentifier(prevSong?.fileName, "raw", packageName)
            mediaPlayer = MediaPlayer.create(this, prevSongId)
            binding.musicDetailsTitle.text = prevSong?.title
            mediaPlayer.start()
            startAnimation()
            binding.musicDetailsSeekbarEnd.text = convertTime(mediaPlayer.duration)

            music = prevSong
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacksAndMessages(null)
        resetMediaPlayer()
    }

    fun startAnimation() {
        val animator = ObjectAnimator.ofFloat(binding.musicDetailsImage, "rotation", 0f, 360f)
        animator.duration = 1000
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator)
        animatorSet.start()
    }

    fun resetMediaPlayer() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    fun convertTime(duration: Int) : String {
        val min = duration / 1000 / 60
        val sec = duration / 1000 % 60

        if(sec < 10) {
            return "$min:0$sec"
        }

        return "$min:$sec"
    }

}