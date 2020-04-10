package com.danal.gitsearch.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.danal.gitsearch.R

/**

 * Created by
 * pppdw
 * on 2020. 4. 10..

 */

class ProgressLoading constructor(context: Context) : Dialog(context){

    init {
        setCanceledOnTouchOutside(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.progress_loading)
    }
}