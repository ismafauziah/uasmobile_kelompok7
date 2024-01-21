package com.example.youtubeapikotlin.ui.video

<<<<<<< HEAD
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
=======
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoFragment {
}

















private var isLoading = false
private var isScroll = false
private var currentItem = -1
private var totalItem = -1
private var scrollOutItem = -1
private var isAllVideoLoaded = false




 val manager = LinearLayoutManager(requireContext())

        binding.rvVideo.LayoutManager = manager

        binding.rvVideo.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState, newstate: Int){
                super.onScrollStateChanged(recyclerView, newState)
                if (newstate = AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScroll = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int){
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItem = manager.itemCount
                scrollOutItem = manager.findFirstVisibleItemPosition()
                if (isScroll && (currentItem + scrollOutItem == totalItem)){
                    isScroll = false
                    if (!isLoading){
                        if (!isAllVideoLoaded){
                            videoViewModel?.getVideoList()
                        }else{
                            Toast.makeText(requireContext(), "All video loaded", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

        })

        videoViewModel?.video?.observe(viewLifecycleOwner, {



        })

        videoViewModel.isLoading?.observe(viewLifecycleOwner, {
            isLoading = it
        })

        videoViewModel?.isAllVideoLoaded?.observe(viewLifecycleOwner, {
            isAllVideoLoaded = it
        })
>>>>>>> bagian5
