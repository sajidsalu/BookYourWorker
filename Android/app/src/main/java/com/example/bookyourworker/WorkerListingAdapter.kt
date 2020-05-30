package com.example.bookyourworker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookyourworker.Model.Worker


class WorkerListingAdapter(var context: Context, var list: List<Worker>, var category: String) :
    RecyclerView.Adapter<WorkerListingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.findViewById<View>(R.id.txtName) as TextView

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WorkerListingAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_worker_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WorkerListingAdapter.ViewHolder, position: Int) {
        list[position].run {
            try {
                holder.tvName.text = name
                holder.tvName.setOnClickListener {
                    val intent = Intent(context, WorkerInfoActivity::class.java).apply {
                        putExtra("category", category)
                        putExtra("name", name)
                    }
                    context.startActivity(intent)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}