package com.example.bookyourworker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookyourworker.Model.Category
import com.example.bookyourworker.Model.DtoCallBack
import com.example.bookyourworker.Model.JobCategory
import com.example.bookyourworker.Model.JobCategoryBase
import com.example.bookyourworker.Services.JobCategoryService
import kotlinx.android.synthetic.main.activity_main.*


private var categories: MutableList<Category> = ArrayList()

private lateinit var adapter: CategoryAdapter

private lateinit var jobCategoryService: JobCategoryService

private lateinit var jobCategories: List<JobCategory>

private lateinit var jobCategoriesForSpinner: MutableList<JobCategory>

private var selectedJobId = 0

private var selectedJobName =""


class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jobCategoryService = JobCategoryService()
        setContentView(R.layout.activity_main)
        getIntentExtra()
        if (jobCategories != null && jobCategories.size > 0) {
            createJobCategoriesForSpinner()
            var spinnerAdapter = SpinnerAdapterJobCategory(jobCategoriesForSpinner, DashboardActivity@ this)
            spnCategories.adapter = spinnerAdapter
            spnCategories.setSelection(0)
        }

        imgDropDown.setOnClickListener {
            spnCategories.performClick()
        }

        spnCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    selectedJobId = jobCategories.get(position).id
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    fun createJobCategoriesForSpinner() {
        jobCategoriesForSpinner = jobCategories.toMutableList();
        var jobCategory: JobCategory = JobCategory("", -1, "Select Work type", "")
        jobCategoriesForSpinner.add(0, jobCategory)
    }


    fun getIntentExtra() {
        if (intent != null) {
            val bundle = intent.extras
            if (bundle != null && bundle.containsKey("JobCategories")) {
                jobCategories = bundle.getSerializable("JobCategories") as ArrayList<JobCategory>
                setAdapter(jobCategories)
            }
        }
    }

    fun getCategoryList() {
        jobCategoryService.getJobCategories(DashboardActivity@ this, object : DtoCallBack<Any?> {
            override fun onParsesResponse(type: Any?) {
                Log.d("response", type.toString())
                try {
                    if (type != null) {
                        var jobCategoryBase: JobCategoryBase = type as JobCategoryBase;
                        if (jobCategoryBase != null && jobCategoryBase.data != null && jobCategoryBase.data.size > 0) {
                            setAdapter(jobCategoryBase.data)
                        }
                        Log.d("API result", jobCategoryBase.data.size.toString())

                    }
                } catch (e: Exception) {

                }
            }


        })
    }

    fun navigateToSearch(id:Int,jobname:String) {


        val intent = Intent(DashboardActivity@ this, WorkerListingActivity::class.java).apply {
            putExtra("categoryName", jobname)
            putExtra("categoryId", id.toString())
        }
        startActivity(intent)
    }

    fun setAdapter(categories: List<JobCategory>) {
        adapter = CategoryAdapter(DashboardActivity@ this, categories, object : DTOCallBack {
            override fun onClick(position: Int) {
                Log.e("List Click", "Selected position{$position}")
                selectedJobId= jobCategories.get(position).id;
                selectedJobName= jobCategories.get(position).jobCategoryTitle
                navigateToSearch(selectedJobId, selectedJobName)
            }
        });
        val layoutManager = GridLayoutManager(this, 2)
        val manager = GridLayoutManager(this, 4, RecyclerView.VERTICAL, false)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_categories.layoutManager = manager
        rv_categories.adapter = adapter
    }


    interface DTOCallBack {

        fun onClick(position: Int);
    }
}
