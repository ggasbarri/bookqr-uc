package com.telecomuc.bookqr.main

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telecomuc.bookqr.R
import com.telecomuc.bookqr.camera.LivePreviewActivity
import com.telecomuc.bookqr.data.BookData
import com.telecomuc.bookqr.detail.DetailActivity
import com.telecomuc.bookqr.koin.mainVmName
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel(name = mainVmName)

    private lateinit var adapter: LastSeenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        setupFab()

        observeData()
    }

    private fun observeData() {

        viewModel.lastSeenBooks.observe(this, Observer {
            adapter.submitList(it)
            if (it != null) {
                if (it.size == 0) {
                    empty_view.visibility = VISIBLE
                } else {
                    empty_view.visibility = GONE
                }
            }
        })
    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false)

        adapter = LastSeenAdapter(
                object : LastSeenItemClick {
                    override fun click(bookData: BookData) {
                        startActivity<DetailActivity>("bookData" to bookData)
                    }
                })

        last_seen_rv.adapter = adapter
        last_seen_rv.layoutManager = layoutManager
    }

    private fun setupFab() {
        camera_fab!!.setOnClickListener { v ->

            val intent = Intent(this@MainActivity, LivePreviewActivity::class.java)
            startActivity(intent)

        }
    }
}
