package com.example.mvpsample.Usage;


import com.example.mvpsample.Calculator.CalcModel;

public interface UsageView {

    void setAdapter(UsageAdapter usageAdapter);

    void updateAdapter(UsageAdapter usageAdapter, CalcModel item);

}