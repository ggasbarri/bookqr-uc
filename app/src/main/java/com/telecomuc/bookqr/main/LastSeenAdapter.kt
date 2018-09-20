package com.telecomuc.bookqr.main

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telecomuc.bookqr.R
import com.telecomuc.bookqr.data.BookData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.main_list_item.*
import org.koin.standalone.KoinComponent

class LastSeenAdapter(val clickListener: LastSeenItemClick)
    : PagedListAdapter<BookData, LastSeenVh>(diffUtil), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastSeenVh {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_list_item, parent, false)

        val viewHolder = LastSeenVh(view)

        view.setOnClickListener { _ ->
            //TODO: Test
            getItem(viewHolder.adapterPosition)?.let {
                clickListener.click(it)
            }
        }

        return viewHolder

    }

    override fun onBindViewHolder(holder: LastSeenVh, position: Int) {
        val bookData = getItem(position)

        bookData?.let {

            holder.bind(bookData)

        } ?: return // TODO: Handle placeholder
    }
}

class LastSeenVh(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(data: BookData) {
        book_tv.text = data.nombre
        author_tv.text = data.autor

    }

}

interface LastSeenItemClick {
    fun click(bookData: BookData)
}

val diffUtil = object : DiffUtil.ItemCallback<BookData>() {

    override fun areItemsTheSame(oldItem: BookData, newItem: BookData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookData, newItem: BookData): Boolean {
        return oldItem == newItem
    }

}