package com.omninos.infiniteloadmore;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manjinder Singh on 11 , December , 2019
 */
public class MyViewModel extends ViewModel {
    Api api = new ApiClient().PlacesBuild(Api.class);

    private MutableLiveData<MyModel> getList;

    public LiveData<MyModel> getData(final Activity activity) {

        getList = new MutableLiveData<>();
        api.getCountry().enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {
                if (response.isSuccessful()) {
                    getList.setValue(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return getList;
    }
}
