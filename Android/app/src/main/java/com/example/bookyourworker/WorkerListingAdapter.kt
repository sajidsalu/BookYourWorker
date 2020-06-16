package com.example.bookyourworker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookyourworker.Model.WorkerDetails
import androidx.core.content.ContextCompat.startActivity




class WorkerListingAdapter(var context: Context, var list: List<WorkerDetails>, var category: String) :
        RecyclerView.Adapter<WorkerListingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView.findViewById<View>(R.id.tvName) as TextView
        var tvMobile = itemView.findViewById<View>(R.id.txtMobile) as TextView
        var imgWorker = itemView.findViewById<View>(R.id.img_worker) as AppCompatImageView
        var imgWhatsApp = itemView.findViewById<View>(R.id.img_whatsApp) as AppCompatImageView
        var imgMail = itemView.findViewById<View>(R.id.imgMail) as AppCompatImageView
        var layMobile = itemView.findViewById<View>(R.id.layMobile) as RelativeLayout

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WorkerListingAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_worker_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WorkerListingAdapter.ViewHolder, position: Int) {
        list[position].run {
            try {
                holder.tvName.text = postTitle
                holder.tvMobile.text = whatsappNumber
                holder.imgMail.setOnClickListener {
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + mailTo));
                        context.startActivity(intent);
                    } catch (e: ActivityNotFoundException) {
                        Log.e("mail issue", e.message)
                    }
                }
                holder.imgWhatsApp.setOnClickListener {
                    try {
//                        val url = "https://api.whatsapp.com/send?phone=$whatsappNumber"
//                        val i = Intent(Intent.ACTION_VIEW)
//                        i.data = Uri.parse(url)
//                        context.startActivity(i)
                        sendWhatsapp()
                    } catch (e: Exception) {
                        Log.e("whatsappissue", e.message);
                    }
                }
                holder.layMobile.setOnClickListener {
                    try{
                        call(whatsappNumber)
                    }catch (e:Exception)
                    {
                        Log.e("call issue",e.message)
                    }

                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }


    private fun sendWhatsapp() {
        try {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.setPackage("com.whatsapp")
            if (sendIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(sendIntent)
            }
        } catch (e: Exception) {
            Log.e("whatsappissue", e.message)
        }
    }

    private fun call(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        context.startActivity(intent)

    }
}