package com.example.bookyourworker.Model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class JobCategory(

        @SerializedName("redirect_to") val redirectTo: String,
        @SerializedName("term_id") val id: Int,
        @SerializedName("job_category_title") val jobCategoryTitle: String,
        @SerializedName("cate_img_path") val imgPath: String
) :  Serializable

data class JobCategoryBase (

        @SerializedName("data") val data : List<JobCategory>
)