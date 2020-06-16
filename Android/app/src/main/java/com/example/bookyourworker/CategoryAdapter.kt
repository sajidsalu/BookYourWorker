package com.example.bookyourworker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bookyourworker.Model.Category
import com.example.bookyourworker.Model.JobCategory
import com.squareup.picasso.Picasso

class CategoryAdapter(var context: Context, var list: List<JobCategory>,var callback:DashboardActivity.DTOCallBack) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.findViewById<View>(R.id.tv_name) as TextView
        var ivImage = itemView.findViewById<View>(R.id.iv_image) as AppCompatImageView
        var layContent = itemView.findViewById<View>(R.id.layContent) as RelativeLayout

        init {
            itemView.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CategoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        list[position].run {
            try {
                holder.tvName.text = jobCategoryTitle
                Picasso.with(context).load(imgPath.toString()).placeholder(R.drawable.no_image_available).into(holder.ivImage)
                holder.layContent.setOnClickListener {
//                    val intent = Intent(context, WorkerListingActivity::class.java).apply {
//                        putExtra("category", jobCategoryTitle)
//                    }
//                    context.startActivity(intent)
                    callback.onClick(position)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}