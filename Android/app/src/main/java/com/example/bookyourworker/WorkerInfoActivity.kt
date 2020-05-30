package com.example.bookyourworker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_worker_info.*

class WorkerInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_info)
        var category = intent.getStringExtra("category")
        var name = intent.getStringExtra("name")
        tv_name.setText(name)
        workType.setText(category)
    }
}
