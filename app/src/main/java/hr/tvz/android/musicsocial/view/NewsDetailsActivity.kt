package hr.tvz.android.musicsocial.view

import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.databinding.ActivityNewsDetailsBinding
import hr.tvz.android.musicsocial.model.News
import hr.tvz.android.musicsocial.viewmodel.UserViewModel

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val news: News? = intent.extras!!.getParcelable("news")

        if(news != null) {
            binding.newsDetailsImage.setImageURI(Uri.parse(news.image))
            binding.newsDetailsTitle.text = news.title
            binding.newsDetailsInfo.text = news.time + " | by " + news.user
            binding.newsDetailsDescription.text = news.description
            binding.newsDetailsUserImage.setImageURI(Uri.parse(UserViewModel.user.value?.image))
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
    }
}