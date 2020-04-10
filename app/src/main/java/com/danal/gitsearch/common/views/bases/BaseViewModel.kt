package com.danal.gitsearch.common.views.bases

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**

 * Created by
 * pppdw
 * on 2020. 4. 9..

 */

open class BaseViewModel : ViewModel(){

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    fun clearDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposable()
        super.onCleared()
    }
}