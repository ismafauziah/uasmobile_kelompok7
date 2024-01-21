package com.example.youtubeapikotlin.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapikotlin.model.ChannelModel

class VideoViewModel : ViewModel() {

    private val _channel = MutableLiveData<ChannelModel>()
    val channel = _channel
    private val _isLoading = MutableLiveData<Boolean>()
    val isiLoading =_isLoading
    private val _message = MutableLiveData<String>()
    val message = _message

    init {
        getChannel()
    }

    private fun getChannel(){
        _isLoading.value = true
        val client = ApiConfig.getService().getChannel("snippet", "UCkXmLjEr95LVtGuIm3l2dPg&key").
        client.enqueue(object : Callback<ChannelModel>{
            override fun onResponse(call: Call<ChannelModel>, response: Response<ChannelModel>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null && data.items.isNotEmpty()){
                        _channel.value = data
                    } else {
                        _message.value = "No channel"
                    }

                } else {
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<ChannelModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "Failed: ", t)
            }
        })
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName

}