package hr.tvz.android.musicsocial.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.databinding.ActivityRegisterBinding
import hr.tvz.android.musicsocial.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.registerLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            val username = binding.registerUsername.text.toString().trim()
            val password = binding.registerPassword.text.toString().trim()
            val image = binding.registerImageURL.text.toString().trim()

            if(checkValidation()) {
                viewModel.registerUser(username, password, image, this)
                // Cek apakah username, password, dan image sudah diisi
                if (username.isNotEmpty() && password.isNotEmpty() && image.isNotEmpty()) {
                    // Jika sudah diisi, arahkan ke halaman LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
            }
        }
    }

}

    private fun checkValidation(): Boolean {
        binding.registerUsername.error = null
        binding.registerPassword.error = null
        binding.registerImageURL.error = null

        if(binding.registerUsername.text.toString().length < 3) {
            binding.registerUsername.error = resources.getString(R.string.register_username_validation)
            return false
        }

        if(binding.registerPassword.text.toString().length < 5) {
            binding.registerPassword.error = resources.getString(R.string.register_password_validation)
            return false
        }

        if(binding.registerImageURL.text.toString().isEmpty()) {
            binding.registerImageURL.error = resources.getString(R.string.register_image_validation)
            return false
        }

        return true
    }
}


