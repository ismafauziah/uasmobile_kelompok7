package com.example.youtubeapikotlin.ui.video

import androidx.lifecycle.MutableLiveData

class VideoViewModel {













private val _isAllVideoLoaded = MutableLiveData<Boolean>()
val isAllVideoLoaded = _isAllVideoLoaded


private var nextPageToken: String? = null

init{
    getVideoList()
}

fun getVideoList(){
}






    if (data != null){
        if (data.nextPageToken != null){
            nextPageToken = data.nextPageToken
        } else {
            _isAllVideoLoaded.value = true
        }
        if (data.items.isNotEmpty()){
            _video.value = data
        }
    }
}

