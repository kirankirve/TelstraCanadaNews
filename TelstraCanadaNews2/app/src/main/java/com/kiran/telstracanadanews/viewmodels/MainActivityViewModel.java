package com.kiran.telstracanadanews.viewmodels;


import android.content.Context;
import android.os.AsyncTask;

import com.kiran.telstracanadanews.models.Row;
import com.kiran.telstracanadanews.repositories.NewsRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Row>> mNews = new MutableLiveData<>();
    private NewsRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();
    private Context context;

    public void init(Context cxt) {
        context = cxt;
        mRepo = NewsRepository.getInstance(context);
        mNews = mRepo.getNews();
    }

    public LiveData<List<Row>> getNewsList() {
        if (mNews == null) {
            mNews = new MutableLiveData<>();
        }
        return mNews;
    }

    public LiveData<Boolean> getIsUpdating() {
        return mIsUpdating;
    }


}
