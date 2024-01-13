package com.example.youtubeapikotlin.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapikotlin.model.ChannelModel
import com.example.youtubeapikotlin.model.VideoYtModel

class VideoViewModel : ViewModel() {

    private val _video = MutableLiveData<VideoYtModel?>()
    val video = _video
    private val _isLoading = MutableLiveData<Boolean>()
    val isiLoading =_isLoading
    private val _message = MutableLiveData<String>()
    val message = _message

    init {
        getVideoList()
    }

    private fun getVideoList(){
        _isLoading.value = true
        val client = ApiConfig.getService().getVideo("snippet", "UCkXmLjEr95LVtGuIm3l2dPg&key", "date").
        client.enqueue(object : Callback<ChannelModel>{
            override fun onResponse(call: Call<VideoYtModel>, response: Response<VideoYtModel) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null && data.items.isNotEmpty()){
                        _channel.value = data
                    } else {
                        _message.value = "No video"
                    }

                } else {
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<VideoYtModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Failed: ", t)
            }
        })
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName

}