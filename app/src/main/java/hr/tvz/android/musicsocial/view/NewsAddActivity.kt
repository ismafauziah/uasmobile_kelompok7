package hr.tvz.android.musicsocial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.databinding.ActivityNewsAddBinding
import hr.tvz.android.musicsocial.viewmodel.NewsViewModel
import hr.tvz.android.musicsocial.viewmodel.UserViewModel

class NewsAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsAddBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.newsAddButton.setOnClickListener {
            val title = binding.newsAddTitle.text.toString().trim()
            val image = binding.newsAddImage.text.toString().trim()
            val description = binding.newsAddDescription.text.toString().trim()

            if(checkValidation()) {
                viewModel.addNewNews(
                    title,
                    description,
                    image,
                    UserViewModel.user.value!!.username,
                    this
                )
            }
        }

        binding.newsAddCancel.setOnClickListener {
            onBackPressed()
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

    fun checkValidation() : Boolean {
        binding.newsAddTitle.error = null
        binding.newsAddImage.error = null
        binding.newsAddDescription.error = null

        if(binding.newsAddTitle.text.toString().isEmpty()) {
            binding.newsAddTitle.error = resources.getString(R.string.news_title_validation)
            return false
        }

        if(binding.newsAddImage.text.toString().isEmpty()) {
            binding.newsAddImage.error = resources.getString(R.string.news_image_validation)
            return false
        }

        if(binding.newsAddDescription.text.toString().isEmpty()) {
            binding.newsAddDescription.error = resources.getString(R.string.news_description_validation)
            return false
        }

        return true
    }
}