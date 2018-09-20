package com.telecomuc.bookqr.detail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.telecomuc.bookqr.R
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.koin.detailVmName
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel(name = detailVmName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // These two properties are mutually exclusive (we have one or the other)
        val id = this.intent.extras?.getString("id")
        val bookData = this.intent.extras?.getParcelable<BookData>("bookData")

        savedInstanceState?.let {
            viewModel.bookData = it.getParcelable("bookData")
        }

        if (id == null) {

            bookData?.let {

                setupData(bookData)

            } ?: setupData(viewModel.bookData!!)

        } else {

            observeBook(id)

        }

    }

    private fun observeBook(id: String) {
        viewModel.getBookForID(id).observe(this, Observer { data ->

            data?.let {

                setupData(it)

            } ?: return@Observer // TODO: Handle null case

        })
    }


    private fun setupData(bookData: BookData) {

        viewModel.bookData = bookData

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.apply {
            this!!.putParcelable("bookData", viewModel.bookData)
        }
    }
}
