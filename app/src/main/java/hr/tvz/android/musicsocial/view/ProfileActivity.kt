package hr.tvz.android.musicsocial.view

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.databinding.ActivityProfileBinding
import hr.tvz.android.musicsocial.viewmodel.UserViewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black)))

        binding.profileUsername.text = UserViewModel.user.value?.username
        binding.profileEmail.text = UserViewModel.user.value?.username?.lowercase() + "@mail.com"
        binding.profileImage.setImageURI(Uri.parse(UserViewModel.user.value?.image))

        binding.profilePlayMusic.setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }

        binding.profileWatchVideos.setOnClickListener {
            startActivity(Intent(this, VideosActivity::class.java))
        }

        binding.profileBrowseNews.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menuItemLogout -> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() { }
}