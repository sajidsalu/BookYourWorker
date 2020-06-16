package com.example.bookyourworker

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.bookyourworker.Model.JobCategory


class SpinnerAdapterJobCategory
/**
 * Constructor for SpinnerAdapterBank.
 *
 * @param list     - list of Bank.
 * @param activity - Activity.
 */
(
        /**
         * list of Bank.
         */
        private val list: List<JobCategory>,
        /**
         * Activity object.
         */
        private val activity: Activity) : BaseAdapter(), android.widget.SpinnerAdapter {

    /**
     * method to get the list count.
     *
     * @return list.size()
     */
    override fun getCount(): Int {
        return list.size
    }

    /**
     * method to get the item.
     *
     * @param arg0 int.
     * @return list.get(arg0)
     */
    override fun getItem(arg0: Int): Any {
        return list[arg0]
    }

    /**
     * method to get the item id.
     *
     * @param position int.
     * @return position
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * get view.
     *
     * @param position    int.
     * @param convertView View.
     * @param parent      ViewGroup.
     * @return spinnerEntry.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = activity.layoutInflater
        val spinnerEntry = inflater.inflate(R.layout.spinner_adapter,null)
        val name = spinnerEntry.findViewById(R.id.txtName) as TextView
        val itemName = list[position]
        name.setText(itemName.jobCategoryTitle)
        if(position==0)
        {
            name.setTextColor(Color.GRAY)
        }else{
            name.setTextColor(Color.BLACK)
        }
        return spinnerEntry
    }

}
