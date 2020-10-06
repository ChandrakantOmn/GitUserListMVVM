package com.foo.assignment.ui.books.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.foo.assignment.R
import com.foo.assignment.data.model.BooksResponse
import com.foo.assignment.data.repository.NetworkState


class BooksAdapter constructor(
    private var isGridView: Boolean,
    private val retryCallback: () -> Unit,
    private val clickListener: (View, BooksResponse.Item) -> Unit
) :
    PagedListAdapter<BooksResponse.Item, RecyclerView.ViewHolder>(UserItemDiffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_user -> BookViewHolder.create(parent, clickListener, isGridView)
            R.layout.item_network_state -> NetworkStateViewHolder.create(
                parent,
                retryCallback
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_user -> (holder as BookViewHolder).bindTo(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_user
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setIsGridView(gridView: Boolean) {
        isGridView = gridView
        notifyDataSetChanged()
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    companion object {
        val UserItemDiffCallback = object : DiffUtil.ItemCallback<BooksResponse.Item>() {
            override fun areItemsTheSame(oldItem: BooksResponse.Item, newItem: BooksResponse.Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: BooksResponse.Item, newItem: BooksResponse.Item): Boolean {
                return oldItem == newItem
            }
        }
    }

}