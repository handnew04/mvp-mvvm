package com.example.mvvmsample.Main;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * Calc Model
 * */

public class MainViewModel extends ViewModel {
    private MutableLiveData<MainModel> mainModel = new MutableLiveData<>();

    public LiveData<MainModel> getMainModel() {
        if (mainModel == null) {
            mainModel = new MutableLiveData<MainModel>();
        }
        return mainModel;
    }

    void setTime() {
        mainModel.setValue(new MainModel(TimeLogic()));
    }

    @SuppressLint("DefaultLocale")
    private String TimeLogic() {
        Calendar calendar = Calendar.getInstance(); // 칼렌다 변수
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return String.format("%d.%d.%d %d시 %d분 %d초",year,month,day,hour,minute,second);
    }
}