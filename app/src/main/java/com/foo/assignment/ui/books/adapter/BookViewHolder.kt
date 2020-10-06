package com.foo.assignment.ui.books.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foo.assignment.R
import com.foo.assignment.data.model.BooksResponse
import kotlinx.android.synthetic.main.item_user.view.*

class BookViewHolder(view: View, private val clickListener: (View, BooksResponse.Item) -> Unit) : RecyclerView.ViewHolder(view) {

    fun bindTo(item: BooksResponse.Item?) {
        itemView.tv_profile_name.text = item?.volumeInfo?.title?:""
        itemView.tv_subtitle.text = item?.volumeInfo?.publisher?:"Not Available"

        Glide.with(itemView.context)
            .load(item?.volumeInfo?.imageLinks?.smallThumbnail)
            .placeholder(R.mipmap.ic_launcher)
            .into(itemView.iv_profile_pic)

        itemView.userContainer.setOnClickListener {
            if (item != null) {
                clickListener(itemView.iv_profile_pic, item)
            }
        }
        ViewCompat.setTransitionName(itemView.iv_profile_pic, item?.volumeInfo?.title)
    }

    companion object {
        fun create(parent: ViewGroup, clickListener: (View, BooksResponse.Item) -> Unit, isGridView: Boolean): BookViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view =
                layoutInflater.inflate(
                    if (isGridView) R.layout.item_user_grid
                    else R.layout.item_user, parent, false
                )
            return BookViewHolder(view, clickListener)
        }
    }

}