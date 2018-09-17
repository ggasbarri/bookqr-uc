package com.telecomuc.bookqr.main

import android.support.v7.recyclerview.extensions.ListAdapter
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

class LastSeenAdapter(val clickListener: View.OnClickListener)
    : ListAdapter<BookData, LastSeenVh>(diffUtil), KoinComponent {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastSeenVh {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_list_item, parent, false)

        view.setOnClickListener(clickListener)

        return LastSeenVh(view)

    }

    override fun onBindViewHolder(holder: LastSeenVh, position: Int) {
        holder.bind(getItem(position))
    }
}

class LastSeenVh(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(data: BookData) {
        book_tv.text = data.nombre
        author_tv.text = data.autor

    }

}

val diffUtil = object : DiffUtil.ItemCallback<BookData>() {

    override fun areItemsTheSame(oldItem: BookData, newItem: BookData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookData, newItem: BookData): Boolean {
        return oldItem == newItem
    }

}