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

class CategoryAdapter(var context: Context, var list: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.findViewById<View>(R.id.tv_name) as TextView
        var ivImage = itemView.findViewById<View>(R.id.iv_image) as AppCompatImageView
        var layContent = itemView.findViewById<View>(R.id.layContent) as RelativeLayout

        init {
            itemView.setOnClickListener {
                //                val intent = Intent(context, WorkerListingActivity::class.java).apply{
//                   putExtra("category",tvName)
//                }
//                context.startActivity(intent)
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
                holder.tvName.text = name
                //1->carpenter 2->electrician 3->plumber
                when (type) {
                    1 -> holder.ivImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.carpenter))
                    2 -> holder.ivImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.electrician))
                    3 -> holder.ivImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.plumber))
                    4 -> holder.ivImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.mason))
                }
                holder.layContent.setOnClickListener {
                    val intent = Intent(context, WorkerListingActivity::class.java).apply {
                        putExtra("category", name)
                    }
                    context.startActivity(intent)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}