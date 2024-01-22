package hr.tvz.android.musicsocial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.tvz.android.musicsocial.api.MusicService
import hr.tvz.android.musicsocial.model.Music
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MusicViewModel: ViewModel() {

    companion object {
        val music = MutableLiveData<List<Music>>()
    }

    private val musicService: MusicService = MusicService()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun refresh() {
        fetchDataFromRemoteApi()
    }

    fun fetchDataFromRemoteApi() {
        disposable.add(
            musicService.getAllMusic()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Music>>(){
                    override fun onSuccess(t: List<Music>) {
                        music.value = t
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