package com.example.mvpsample.Main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.mvpsample.Calculator.Calc;
import com.example.mvpsample.Calculator.CalcModel;
import com.example.mvpsample.Usage.UsageActivity;

import java.util.Calendar;

/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * CalcMain Presenter
 * */
class MainPresenter {
    private final String TAG = MainPresenter.class.getSimpleName();

    private MainView mainView;
    private CalcModel calcModel;
    private MainModel mainModel;

    MainPresenter(MainView mainView) {
        this.mainView  = mainView;
        this.calcModel = new CalcModel();
        this.mainModel = new MainModel();
    }

    void onItemClick(String item) {
        if (mainView != null) {
            CalcLogic(item);
            mainView.setInputData(calcModel.getInputData());
            mainView.setResultData(calcModel.getOutputData());
        }
    }

    void onStartActivity(Activity activity) {
        Intent intent = new Intent(activity, UsageActivity.class);
        intent.putExtra("CalcModel",calcModel);
        activity.startActivity(intent);
    }

//    void getTime() {
//        TimeLogic();
//    }

    private void CalcLogic(String item) {
        Log.d(TAG,">>>>"+item);
        if ("←".equals(item)) {
            if (calcModel.getInputData().length() > 0) {
                calcModel.setInputData(calcModel.getInputData().substring(0, calcModel.getInputData().length()-1));
            }
            try {
                calcModel.setOutputData(new Calc().processStr(calcModel.getInputData()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("=".equals(item)) {
            try {
                calcModel.setOutputData(new Calc().processStr(calcModel.getInputData()));
                calcModel.addUsage(calcModel.getInputData(), calcModel.getOutputData());
                calcModel.setInputData("");
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if ("0".equals(calcModel.getInputData())) {
                calcModel.setInputData(item);
            } else {
                calcModel.setInputData(calcModel.getInputData() + item);
            }
            try {
                calcModel.setOutputData(new Calc().processStr(calcModel.getInputData()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private void TimeLogic() {
        Calendar calendar = Calendar.getInstance(); // 칼렌다 변수
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        mainModel.setNowTime(String.format("%d.%d.%d %d시 %d분 %d초",year,month,day,hour,minute,second));
//        mainView.setTime(mainModel.getNowTime());
    }

    void onDestroy() {
        mainView = null;
    }
}