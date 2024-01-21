package com.example.youtubeapikotlin.ui.video

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
