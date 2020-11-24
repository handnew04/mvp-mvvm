package com.example.mvpsample.Usage;

import android.app.Activity;
import android.util.Log;

import com.example.mvpsample.Calculator.CalcModel;

class UsagePresenter {
    private final String TAG = UsagePresenter.class.getSimpleName();

    private UsageView usageView;
    private CalcModel calcModel;

    private UsageAdapter usageAdapter;

    UsagePresenter(UsageView usageView) {
        this.usageView  = usageView;
        this.calcModel = new CalcModel();
        usageAdapter = new UsageAdapter();
    }

    void setAdapter() {
        usageView.setAdapter(usageAdapter);
    }

    void updateAdapter() {
        usageView.updateAdapter(usageAdapter, calcModel);
    }

    void setModel(Activity activity) {
        calcModel = activity.getIntent().getParcelableExtra("CalcModel");
        if (calcModel == null) {
            calcModel = new CalcModel();
        }

        Log.d(TAG,calcModel.getOutputData());
        for (CalcModel.CalcResult result : calcModel.getCalcResultList().getResultUsage()) {
            Log.d(TAG,result.toString());
        }
    }

    void onDestroy() {
        usageView = null;
    }
}