package com.example.bookyourworker.Model

import com.google.gson.annotations.SerializedName

data class WorkerDetails(
        @SerializedName("post_title") val postTitle: String,
        @SerializedName("term_id") val termId: Int,
        @SerializedName("post_id") val postId: Int,
        @SerializedName("post_content") val postContent: String,
        @SerializedName("post_path") val postPath: String,
        @SerializedName("whatsapp_number") val whatsappNumber: String,
        @SerializedName("whatsapp_icon") val whatsappIcon: String,
        @SerializedName("call_icon") val callIcon: String,
        @SerializedName("mailto") val mailTo: String
)

data class WorkerDetailsBase(
        @SerializedName("data") val data: List<WorkerDetails>
)