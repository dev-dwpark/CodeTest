package com.danal.gitsearch.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**

 * Created by
 * pppdw
 * on 2020. 4. 10..

 */

open class OnScrollRefreshListener : RecyclerView.OnScrollListener(){

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val last = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
        val total = recyclerView.adapter!!.itemCount

        if (last == total-5) {
            isRefresh()
        }
    }

    open fun isRefresh(){

    }
}