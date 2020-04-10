package com.danal.gitsearch.model

import com.google.gson.annotations.SerializedName


/**
 * Created by
 * pppdw
 * on 2020. 4. 9..
 */

class GitSearchModel : BaseModel(){
    var requestCode:Int = 0
    @SerializedName("items")
    var items = listOf<Items>() as List<Items>

    class Items{
        @SerializedName("owner")
        var owner = Owners()
        @SerializedName("score")
        var score = 0
        @SerializedName("html_url")
        var htmlUrl = ""
    }

    class Owners{
        @SerializedName("login")
        var login = ""
        @SerializedName("avatar_url")
        var avatarUrl = ""
        @SerializedName("repos_url")
        var reposUrl = ""
    }
}