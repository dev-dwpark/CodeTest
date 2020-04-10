package com.danal.gitsearch.connect

import com.danal.gitsearch.connect.bases.Apis
import com.danal.gitsearch.model.BaseModel
import com.danal.gitsearch.model.GitSearchModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**

 * Created by
 * pppdw
 * on 2020. 4. 9..

 */

class Connect{

    companion object {

        private lateinit var responseCallback : OnResponseCallback

        interface OnResponseCallback {
            fun succ(model : BaseModel)
            fun failed()
        }

        fun callback(responseCallback: OnResponseCallback) : Connect.Companion{
            this.responseCallback = responseCallback
            return this
        }

        fun requestGitSearch(keyword : String, page: Int) : Disposable{
            return Apis.requestSearchGitRepository(keyword, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ response: GitSearchModel ->

                    if(::responseCallback.isInitialized){
                        responseCallback.succ(response)
                    }


                }, { error: Throwable ->
                    if(::responseCallback.isInitialized){
                        responseCallback.failed()
                    }
                })
        }
    }

}