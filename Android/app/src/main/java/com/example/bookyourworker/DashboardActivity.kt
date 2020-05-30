package com.example.bookyourworker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookyourworker.Model.Category
import kotlinx.android.synthetic.main.activity_main.*

private var categories: MutableList<Category> = ArrayList()

private lateinit var adapter: CategoryAdapter

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCategoryList()

    }

    fun setCategoryList() {
        var category = Category("Carpenter", 1);
        categories.add(category)
        category = Category("Electrician", 2);
        categories.add(category)
        category = Category("Plumber", 3);
        categories.add(category)
        category = Category("Mason", 4);
        categories.add(category)
        setAdapter()

        //1->carpenter 2->electrician 3->plumber
    }

    fun setAdapter() {
        adapter = CategoryAdapter(DashboardActivity@ this, categories);
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_categories.layoutManager = layoutManager
        rv_categories.adapter = adapter

    }
}
