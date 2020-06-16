package com.example.bookyourworker

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookyourworker.Model.DtoCallBack
import com.example.bookyourworker.Model.Worker
import com.example.bookyourworker.Model.WorkerDetails
import com.example.bookyourworker.Model.WorkerDetailsBase
import com.example.bookyourworker.Services.JobCategoryService
import kotlinx.android.synthetic.main.activity_worker_listing.*


class WorkerListingActivity : AppCompatActivity() {

    private var workers: MutableList<Worker> = ArrayList()

    private lateinit var adapter: WorkerListingAdapter
    var categoryName = ""
    var categoryId = ""

    lateinit var jobCategoryService: JobCategoryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_listing)
        jobCategoryService = JobCategoryService()
        categoryName = intent.getStringExtra("categoryName")
        categoryId = intent.getStringExtra("categoryId")
        txtScreenTitle.setText(categoryName)
        getCategoryList()
        //setWorkersList()
    }


    fun setAdapter(workers: List<WorkerDetails>) {
        adapter = WorkerListingAdapter(WorkerListingActivity@ this, workers, categoryName)
        rv_workers.layoutManager = LinearLayoutManager(WorkerListingActivity@ this)
        val dividerItemDecoration = DividerItemDecoration(WorkerListingActivity@ this, LinearLayoutManager.VERTICAL)
        rv_workers.addItemDecoration(dividerItemDecoration)
        rv_workers.adapter = adapter
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
//        adapter = WorkerListingAdapter(WorkerListingActivity@ this, workers, categoryName)
//        rv_workers.layoutManager = LinearLayoutManager(WorkerListingActivity@ this)
//        val dividerItemDecoration = DividerItemDecoration(WorkerListingActivity@ this, LinearLayoutManager.VERTICAL)
//        rv_workers.addItemDecoration(dividerItemDecoration)
//        rv_workers.adapter = adapter
    }


    fun getCategoryList() {
        jobCategoryService.getWorkerDetails(DashboardActivity@ this, categoryId, object : DtoCallBack<Any?> {
            override fun onParsesResponse(type: Any?) {
                Log.d("response", type.toString())
                try {
                    progressBar.visibility = View.GONE
                    if (type != null) {
                        var workerDetailsBase: WorkerDetailsBase = type as WorkerDetailsBase;
                        if (workerDetailsBase != null && workerDetailsBase.data != null && workerDetailsBase.data.size > 0) {
                            setAdapter(workerDetailsBase.data)
                        } else {
                            img_noData.visibility = View.VISIBLE
                        }
                        Log.d("API result", workerDetailsBase.data.size.toString())

                    } else {
                        img_noData.visibility = View.VISIBLE
                    }
                } catch (e: Exception) {
                    img_noData.visibility = View.VISIBLE
                }
            }


        })
    }

}
