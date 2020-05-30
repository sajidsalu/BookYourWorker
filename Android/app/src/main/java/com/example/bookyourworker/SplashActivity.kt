package com.example.bookyourworker

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val mHandler = Handler()
    private val mHandlerProgress = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)

        mHandlerProgress.postDelayed(object : Runnable {
            override fun run() {
                showProgress()
            }

        }, 1000)
        mHandler.postDelayed(object : Runnable {
            override fun run() {
                navigate()
            }

        }, 4000)
    }

    fun showProgress() {
        simpleProgressBar.visibility = View.VISIBLE
    }

    fun navigate() {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            //putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
        finish();
    }
}

