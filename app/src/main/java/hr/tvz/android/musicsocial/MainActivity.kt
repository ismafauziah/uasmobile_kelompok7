package hr.tvz.android.musicsocial

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.tvz.android.musicsocial.view.LoginActivity
import hr.tvz.android.musicsocial.view.MusicActivity
import hr.tvz.android.musicsocial.view.NewsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}