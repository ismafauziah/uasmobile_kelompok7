package hr.tvz.android.musicsocial.helper

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.view.LoginActivity

class RegistrationDialog {

    companion object {

        fun showRegistrationDialog(context: Context) {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.registration_dialog, null)

            val dialogBuilder = AlertDialog.Builder(context).setView(dialogView)

            val dialog = dialogBuilder.show()

            dialogView.findViewById<Button>(R.id.registrationCompletedButton).setOnClickListener {
                dialog.dismiss()
                context.startActivity(Intent(context.applicationContext, LoginActivity::class.java))
            }
        }

    }

}