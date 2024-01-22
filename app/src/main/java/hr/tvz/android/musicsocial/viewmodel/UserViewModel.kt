package hr.tvz.android.musicsocial.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.tvz.android.musicsocial.R
import hr.tvz.android.musicsocial.api.UserService
import hr.tvz.android.musicsocial.helper.RegistrationDialog
import hr.tvz.android.musicsocial.helper.Toast
import hr.tvz.android.musicsocial.model.User
import hr.tvz.android.musicsocial.view.ProfileActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

    companion object {
        val user = MutableLiveData<User>()
    }

    private val userService: UserService = UserService()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun loginUser(username: String, password: String, context: Context) {
        disposable.add(
            userService.loginUser(username, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<User>(){
                    override fun onSuccess(t: User) {
                        user.value = t
                        Toast.createSuccessToast(context, context.resources.getString(R.string.toast_login_success))
                        context.startActivity(Intent(context.applicationContext, ProfileActivity::class.java))
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.createErrorToast(context, context.resources.getString(R.string.toast_login_error))
                    }

                })
        )
    }

    fun registerUser(username: String, password: String, image: String, context: Context) {
        disposable.add(
            userService.registerUser(username, password, image)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<User>(){
                    override fun onSuccess(t: User) {
                        user.value = t
                        Toast.createSuccessToast(context, context.resources.getString(R.string.toast_register_success))
                        RegistrationDialog.showRegistrationDialog(context)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Toast.createErrorToast(context, context.resources.getString(R.string.toast_register_error))
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}