package com.example.bookyourworker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.bookyourworker.Model.DtoCallBack
import com.example.bookyourworker.Model.JobCategory
import com.example.bookyourworker.Model.JobCategoryBase
import com.example.bookyourworker.Services.JobCategoryService
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    private val mHandler = Handler()
    private val mHandlerProgress = Handler()

    private lateinit var jobCategoryService: JobCategoryService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        jobCategoryService=JobCategoryService()
        mHandlerProgress.postDelayed(object : Runnable {
            override fun run() {
                getCategoryList()
            }

        }, 1000)

    }

    fun showProgress() {
        simpleProgressBar.visibility = View.VISIBLE
    }

    fun navigate(categories:List<JobCategory>)
    {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("JobCategories",categories as ArrayList)
        }
        startActivity(intent)
        finish();
    }
    fun getCategoryList()
    {
      jobCategoryService.getJobCategories(DashboardActivity@this,object: DtoCallBack<Any?> {
            override fun onParsesResponse(type: Any?) {
                Log.d("response",type.toString())
                try {
                    if (type != null) {
                        var jobCategoryBase: JobCategoryBase = type as JobCategoryBase;
                        if(jobCategoryBase!=null && jobCategoryBase.data!=null && jobCategoryBase.data.size>0)
                        {
                            simpleProgressBar.visibility=View.GONE
                            navigate(jobCategoryBase.data)
                        }
                        Log.d("API result",jobCategoryBase.data.size.toString())

                    }
                }catch (e: Exception)
                {

                }
            }

        })
    }
}

