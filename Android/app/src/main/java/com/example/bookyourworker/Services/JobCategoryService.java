package com.example.bookyourworker.Services;

import android.content.Context;
import android.util.Log;

import com.example.bookyourworker.Model.DtoCallBack;
import com.example.bookyourworker.Model.JobCategoryBase;
import com.example.bookyourworker.Model.WorkerDetailsBase;
import com.example.bookyourworker.Utils.RetrofitWebClient;
import com.google.gson.Gson;

public class JobCategoryService {
    private RetrofitWebClient retrofitWebClient;

    private String URL = "api-job-category.php";
    private String WORKER_DETAILS_URL = "api-worker-list-by-job-category.php?search_category=";

    public void getJobCategories(Context context, final DtoCallBack dtoCallBack) {
        retrofitWebClient = new RetrofitWebClient(context);
        retrofitWebClient.getApiResponse(context, URL, new ResponseCallbackCustom() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.e("APISuccess", "success");
                    Gson gson = new Gson();
                    JobCategoryBase jobCategoryBase = gson.fromJson(result, JobCategoryBase.class);
                    dtoCallBack.onParsesResponse(jobCategoryBase);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(String error) {
                Log.e("APIerror", "failed");
                dtoCallBack.onParsesResponse("");
            }
        });


    }

    /**
     * API call to get the workers list for a job id
     *
     * @param context
     * @param jobId
     * @param dtoCallBack
     */
    public void getWorkerDetails(Context context, String jobId, final DtoCallBack dtoCallBack) {
        retrofitWebClient = new RetrofitWebClient(context);
        retrofitWebClient.getApiResponse(context, WORKER_DETAILS_URL + jobId, new ResponseCallbackCustom() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.e("APISuccessForDetails", "success");
                    Gson gson = new Gson();
                    WorkerDetailsBase workerDetailsBase = gson.fromJson(result, WorkerDetailsBase.class);
                    dtoCallBack.onParsesResponse(workerDetailsBase);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(String error) {
                Log.e("APIerror", "failed");
                dtoCallBack.onParsesResponse("");
            }
        });


    }


}
