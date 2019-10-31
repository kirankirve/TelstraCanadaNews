package com.kiran.telstracanadanews.repositories;


import android.content.Context;

import com.kiran.telstracanadanews.models.CanadaNews;
import com.kiran.telstracanadanews.models.Row;
import com.kiran.telstracanadanews.network.NetworkClient;
import com.kiran.telstracanadanews.network.NetworkInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Singletone Pattern
public class NewsRepository {
    private static NewsRepository instance;
    private ArrayList<Row> dataSet = new ArrayList<>();
    private NetworkInterface newsApi;

    public static NewsRepository getInstance(Context cxt) {
        if (instance == null) {
            instance = new NewsRepository();
        }
        return instance;
    }

    public NewsRepository() {
        newsApi = NetworkClient.createService(NetworkInterface.class);
    }

    public MutableLiveData<List<Row>> getNews() {
        MutableLiveData<List<Row>> newsData = new MutableLiveData<>();
        newsApi.fetchNewsRequestApi().enqueue(new Callback<CanadaNews>() {
            @Override
            public void onResponse(Call<CanadaNews> call,
                                   Response<CanadaNews> response) {
                if (response.isSuccessful()) {
                    CanadaNews canadaNews = (CanadaNews) response.body();
                    dataSet = (ArrayList<Row>) canadaNews.getRows();
                    newsData.setValue(dataSet);
                }
            }

            @Override
            public void onFailure(Call<CanadaNews> call, Throwable t) {
                newsData.setValue(null);

            }
        });
        return newsData;
    }
}
