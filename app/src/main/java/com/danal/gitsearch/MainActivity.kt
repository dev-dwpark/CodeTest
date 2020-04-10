package com.danal.gitsearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.danal.gitsearch.adapt.GitSearchRecycleAdapt
import com.danal.gitsearch.common.OnScrollRefreshListener
import com.danal.gitsearch.common.ProgressLoading
import com.danal.gitsearch.model.GitSearchModel
import com.danal.gitsearch.view_model.GitSearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by
 * pppdw
 * on 2020. 4. 9..
 */

class MainActivity : AppCompatActivity() {
    private lateinit var progressLoading : ProgressLoading
    private lateinit var gitSearchViewModel : GitSearchViewModel
    private val adapt = GitSearchRecycleAdapt()

    private val observeListener = Observer<GitSearchModel>{
        it?.let{
            progressLoading.hide()
            adapt.push(it.items)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressLoading = ProgressLoading(this)

        gitSearchViewModel = ViewModelProviders.of(this).get(GitSearchViewModel::class.java)
        if(::gitSearchViewModel.isInitialized){
            gitSearchViewModel.model.observe(this, observeListener)
        }

        adapt.clear()

        val llm = LinearLayoutManager(this)
        rv_git.layoutManager = llm
        rv_git.setHasFixedSize(true)
        rv_git.adapter = adapt
        rv_git.addOnScrollListener(onScrollRefreshListener)

        bt_search.setOnClickListener {
            search()
        }
    }

    fun search(){
        progressLoading.show()
        adapt.clear()
        gitSearchViewModel.request(et_search.text.toString())
    }

    val onScrollRefreshListener : OnScrollRefreshListener =
        object : OnScrollRefreshListener(){
            override fun isRefresh() {
                super.isRefresh()
                progressLoading.show()
                gitSearchViewModel.requestNextPage()
            }
        }

}
