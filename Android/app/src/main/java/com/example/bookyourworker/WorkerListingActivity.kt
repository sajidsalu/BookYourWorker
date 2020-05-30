package com.example.bookyourworker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookyourworker.Model.Worker
import kotlinx.android.synthetic.main.activity_worker_listing.*


class WorkerListingActivity : AppCompatActivity() {

    private var workers: MutableList<Worker> = ArrayList()

    private lateinit var adapter: WorkerListingAdapter
    var category=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_listing)
         category = intent.getStringExtra("category")
        txtScreenTitle.setText(category)
        setWorkersList()
    }

    fun setWorkersList() {
        workers = ArrayList()
        var worker = Worker("Dave");
        workers.add(worker)
        worker = Worker("George");
        workers.add(worker)
        worker = Worker("Flynn");
        workers.add(worker)
        worker = Worker("John");
        workers.add(worker)
        worker = Worker("Edward");
        workers.add(worker)
        worker = Worker("James");
        workers.add(worker)
        worker = Worker("Lynn");
        workers.add(worker)
        worker = Worker("Mance");
        workers.add(worker)
        worker = Worker("Paul");
        workers.add(worker)
        worker = Worker("Murphy");
        workers.add(worker)
        adapter = WorkerListingAdapter(WorkerListingActivity@ this, workers,category)
        rv_workers.layoutManager = LinearLayoutManager(WorkerListingActivity@ this)
        val dividerItemDecoration = DividerItemDecoration(WorkerListingActivity@ this, LinearLayoutManager.VERTICAL)
        rv_workers.addItemDecoration(dividerItemDecoration)
        rv_workers.adapter = adapter
    }
}
