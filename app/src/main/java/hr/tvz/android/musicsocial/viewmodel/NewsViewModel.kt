package hr.tvz.android.musicsocial.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.api.NewsService
import hr.tvz.android.musicsocial.helper.Toast
import hr.tvz.android.musicsocial.model.News
import hr.tvz.android.musicsocial.view.NewsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NewsViewModel: ViewModel() {

    val news = MutableLiveData<List<News>>()

    private val newsService: NewsService = NewsService()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun refresh() {
        fetchDataFromRemoteApi()
    }

    fun fetchDataFromRemoteApi() {
        disposable.add(
            newsService.getAllNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<News>>(){
                    override fun onSuccess(t: List<News>) {
                        news.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

    fun addNewNews(title: String, description: String, image: String, user: String, context: Context) {
        disposable.add(
            newsService.addNewNews(title, description, image, user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<News>(){
                    override fun onSuccess(t: News) {
                        Toast.createSuccessToast(context, context.resources.getString(R.string.toast_news_added))
                        context.startActivity(Intent(context.applicationContext, NewsActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.createErrorToast(context, context.resources.getString(R.string.toast_news_error))
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}