package com.telecomuc.bookqr.main

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.VISIBLE
import com.telecomuc.bookqr.R
import com.telecomuc.bookqr.camera.CameraActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel(name = "MainViewModel")

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
            if (it != null && it.size == 0) {
                empty_view.visibility = VISIBLE
            }
        })
    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false)

        adapter = LastSeenAdapter(
                View.OnClickListener {
                    // TODO: Handle
                })

        last_seen_rv.adapter = adapter
        last_seen_rv.layoutManager = layoutManager
    }

    private fun setupFab() {
        camera_fab!!.setOnClickListener { v ->

            val intent = Intent(this@MainActivity, CameraActivity::class.java)
            startActivity(intent)

        }
    }
}
