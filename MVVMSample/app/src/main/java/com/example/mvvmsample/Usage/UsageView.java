package com.example.mvvmsample.Usage;

import com.example.mvvmsample.Calculator.CalcModel;

import java.util.ArrayList;

/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * CalcMain Activity
 * */
public interface UsageView {

    void setAdapter();

    void setCalcViewModel();

    void updateAdapter(ArrayList<CalcModel.CalcResult> list);

}