package com.danal.gitsearch.connect.bases

import com.danal.gitsearch.model.GitSearchModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**

 * Created by
 * pppdw
 * on 2020. 4. 9..

 */

class Apis{
    interface Get{
        @GET("/search/repositories")
        fun requestSearchGitRepository(@Query("q") keyword: String
                                    , @Query("page") page: Int
                                    , @Query("my_client_id") myClientId: String) : Observable<GitSearchModel>
    }

    companion object {
        fun requestSearchGitRepository(keyword: String, page: Int): Observable<GitSearchModel> {
            return Creator.create(
                Get::class.java
            ).requestSearchGitRepository(keyword, page, "danal_gitsearch_proj1")
        }
    }
}