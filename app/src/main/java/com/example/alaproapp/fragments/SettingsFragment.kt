package com.example.alaproapp.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.alaproapp.R
import com.example.alaproapp.databinding.FragmentNotificationsBinding
import com.example.alaproapp.sharedPreferenceManager.ModeSharedPref

class SettingsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private var isNight: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mod: String
        val modeSharedPref = ModeSharedPref(requireContext())
        mod = modeSharedPref.getMode()

        if (mod == "Night") {
            binding.switch3.isChecked = true
            isNight = true
        }

        binding.switch3.setOnClickListener {
            if (isNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                modeSharedPref.saveMode("Light")
                binding.switch3.isChecked = false
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                modeSharedPref.saveMode("Night")
                binding.switch3.isChecked = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showExitDialog(langg: String) {
        // Bagian kode yang terkait dengan dialog pemilihan bahasa dihapus
        val d = AlertDialog.Builder(requireContext())
        d.apply {
            setTitle(getString(R.string.exit_txt) + "?")
            setMessage(getString(R.string.exit_mess_txt))
            setPositiveButton(getString(R.string.yes_txt)) { _, _ ->
                // Kode tersisa yang menyimpan bahasa dan menutup aktivitas
            }
            setNegativeButton(getString(R.string.no_txt)) { dia, _ ->
                dia.dismiss()
            }
        }.create().show()
    }
}
