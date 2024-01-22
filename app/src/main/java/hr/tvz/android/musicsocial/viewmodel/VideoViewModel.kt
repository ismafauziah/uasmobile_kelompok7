package hr.tvz.android.musicsocial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.tvz.android.musicsocial.api.VideoService
import hr.tvz.android.musicsocial.model.Video
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class VideoViewModel: ViewModel() {

    val videos = MutableLiveData<List<Video>>()

    private val videoService: VideoService = VideoService()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun refresh() {
        fetchDataFromRemoteApi()
    }

    fun fetchDataFromRemoteApi() {
        disposable.add(
            videoService.getAllVideos()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Video>>(){
                    override fun onSuccess(t: List<Video>) {
                        videos.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}