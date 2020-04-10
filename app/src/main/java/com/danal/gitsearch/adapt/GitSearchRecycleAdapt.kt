package com.danal.gitsearch.adapt

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danal.gitsearch.R
import com.danal.gitsearch.model.GitSearchModel
import kotlinx.android.synthetic.main.item_git.view.*

/**

 * Created by
 * pppdw
 * on 2020. 4. 9..

 */

class GitSearchRecycleAdapt : RecyclerView.Adapter<GitSearchRecycleAdapt.Holder>() {

    var items = mutableListOf<GitSearchModel.Items>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GitSearchRecycleAdapt.Holder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_git, parent, false)

        return Holder(v)
    }

    fun push(items_ : List<GitSearchModel.Items>) {
        this.items.addAll(items_)
        notifyDataSetChanged()
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GitSearchRecycleAdapt.Holder, position: Int) {
        val v = holder.v
        val item = items[position]
        val owner = item.owner

        v.tv_user_id.text = owner.login
        v.tv_user_score.text = item.score.toString()

        val iv = v.iv_user_image
        Glide.with(iv.context).load(owner.avatarUrl).placeholder(R.drawable.ic_launcher_round).into(iv)

        v.setOnClickListener { v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.htmlUrl))) }
    }

    class Holder(val v: View) : RecyclerView.ViewHolder(v)
}
