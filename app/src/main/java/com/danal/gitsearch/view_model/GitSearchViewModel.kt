package com.danal.gitsearch.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danal.gitsearch.common.views.bases.BaseViewModel
import com.danal.gitsearch.connect.Connect
import com.danal.gitsearch.model.BaseModel
import com.danal.gitsearch.model.GitSearchModel

/**
 * Created by
 * pppdw
 * on 2020. 4. 9..
 */

class GitSearchViewModel : BaseViewModel(){
    private val _model = MutableLiveData<GitSearchModel>()
    val model : LiveData<GitSearchModel> = _model

    var page = 1
    var keyword = ""

    fun requestNextPage(){
        page +=page
        addDisposable(Connect.callback(onResponseCallback).requestGitSearch(keyword, page))
    }

    fun request(keyword : String){
        this.keyword = keyword
        this.page = 1
        addDisposable(Connect.callback(onResponseCallback).requestGitSearch(keyword, page))
    }

    private val onResponseCallback = object : Connect.Companion.OnResponseCallback {
        override fun succ(model: BaseModel) {
            _model.value = model as GitSearchModel
        }

        override fun failed() {
            val failedModel = GitSearchModel()
            failedModel.requestCode = 9999
            _model.value = failedModel
        }
    }

}