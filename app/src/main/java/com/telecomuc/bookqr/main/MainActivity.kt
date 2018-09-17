package com.telecomuc.bookqr.main

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.telecomuc.bookqr.R
import com.telecomuc.bookqr.camera.CameraActivity
import com.telecomuc.bookqr.koin.mainModule
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: ViewModel by viewModel(name = "MainViewModel")

    private val adapter: LastSeenAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin(this, listOf(mainModule))

        //TODO: Set empty view

        setupRecyclerView()

        setupFab()
    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false)

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
