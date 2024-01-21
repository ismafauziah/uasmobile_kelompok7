package com.example.youtubeapikotlin.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapikotlin.databinding.FragmentHomeBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val videoViewModel =
            ViewModelProvider(this).get(VideoViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val textView: TextView = binding.textHome
<<<<<<< HEAD:app/src/main/java/com/example/youtubeapikotlin/ui/home/HomeFragment.kt
        homeViewModel.channel.observe(viewLifecycleOwner) {
            if (it != null && it.items.isNotEmpty()){
                it.items.forEach { channel ->
                    textView.text = channel.snippet.title
                }
            }
=======
        videoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
>>>>>>> bagian3:app/src/main/java/com/example/youtubeapikotlin/ui/video/VideoFragment.kt
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}