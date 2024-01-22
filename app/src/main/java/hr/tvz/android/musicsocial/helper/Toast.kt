package hr.tvz.android.musicsocial.helper

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import hr.tvz.android.musicsocial.R

class Toast {

    companion object {

        fun createErrorToast(context: Context, message: String) {
            val toast = Toast(context.applicationContext)

            val view = LayoutInflater.from(context.applicationContext).inflate(R.layout.toast_error, null)

            val toastMessage = view.findViewById<TextView>(R.id.toastMessage)
            toastMessage.text = message

            toast.view = view
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }

        fun createSuccessToast(context: Context, message: String) {
            val toast = Toast(context.applicationContext)

            val view = LayoutInflater.from(context.applicationContext).inflate(R.layout.toast_success, null)

            val toastMessage = view.findViewById<TextView>(R.id.toastMessage)
            toastMessage.text = message

            toast.view = view
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
        }

    }

}