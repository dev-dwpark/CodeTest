package com.danal.gitsearch.connect.bases

import com.danal.gitsearch.BuildConfig
import com.danal.gitsearch.connect.Urls
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**

 * Created by
 * pppdw
 * on 2020. 4. 9..

 */

class Creator{

    companion object {
        private fun retrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Urls.RETROFIT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
        }

        fun <T> create(service: Class<T>): T {
            return retrofit()
                .create(service)
        }

        private fun getClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build()
        }
    }
}