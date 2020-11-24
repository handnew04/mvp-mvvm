package com.example.mvvmsample.Calculator;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * Calc Model
 * */
public class CalcViewModel extends ViewModel {
    private final String TAG = CalcViewModel.class.getSimpleName();

    private MutableLiveData<CalcModel> calcModel = new MutableLiveData<>();

    public LiveData<CalcModel> getCalcModel() {
        if (calcModel == null) {
            calcModel = new MutableLiveData<CalcModel>();
        }
        return calcModel;
    }

    public void setModel(Activity activity) {
        calcModel.setValue(activity.getIntent().getParcelableExtra("CalcModel"));
        for (CalcModel.CalcResult result : calcModel.getValue().getCalcResultList().getResultUsage()) {
            Log.d(TAG,result.toString());
        }
    }

    public void setCalc(String item) {
        if (calcModel.getValue() != null) {
            calcModel.setValue(CalcLogic(item));
        } else {
            calcModel.setValue(new CalcModel(item,item,new CalcModel.CalcResultList()));
        }
    }

    private CalcModel CalcLogic(String item) {
        CalcModel newCalc = new CalcModel();
        if ("←".equals(item)) {
            if (calcModel.getValue().getInputData().length() > 0) {
                calcModel.getValue().setInputData(calcModel.getValue().getInputData().substring(0, calcModel.getValue().getInputData().length()-1));
            }
            try {
                calcModel.getValue().setOutputData(new Calc().processStr(calcModel.getValue().getInputData()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("=".equals(item)) {
            try {
                calcModel.getValue().setOutputData(new Calc().processStr(calcModel.getValue().getInputData()));
                calcModel.getValue().addUsage(calcModel.getValue().getInputData(), calcModel.getValue().getOutputData());
                calcModel.getValue().setInputData("");
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if ("0".equals(calcModel.getValue().getInputData())) {
                calcModel.getValue().setInputData(item);
            } else {
                calcModel.getValue().setInputData(calcModel.getValue().getInputData() + item);
            }
            try {
                calcModel.getValue().setOutputData(new Calc().processStr(calcModel.getValue().getInputData()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        newCalc.setInputData(calcModel.getValue().getInputData());
        newCalc.setOutputData(calcModel.getValue().getOutputData());
        newCalc.getCalcResultList().setResultUsage(calcModel.getValue().getCalcResultList().getResultUsage());

        return newCalc;
    }
}

// using : CalcViewModel calcViewModel = CalcViewModelProviders.of(this).get(CalcViewModel.class)
// calcViewModel.(variable)