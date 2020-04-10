package com.danal.gitsearch.common

import android.app.Application

/**

 * Created by
 * pppdw
 * on 2020. 4. 10..

 */

class App : Application() {
    init {
        Instance = this
    }

    companion object {
        lateinit var Instance: App
    }
}