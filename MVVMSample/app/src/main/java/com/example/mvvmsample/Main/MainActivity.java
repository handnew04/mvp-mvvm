package com.example.mvvmsample.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmsample.Base.BaseActivity;
import com.example.mvvmsample.Calculator.CalcViewModel;
import com.example.mvvmsample.R;
import com.example.mvvmsample.Usage.UsageActivity;
import com.example.mvvmsample.databinding.ActivityMainBinding;


/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * CalcMain Activity
 * */
public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainView {
    private final String TAG = MainActivity.class.getSimpleName();
    private final int TIME_TICK = 1000;

    private CalcViewModel calcViewModel;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(getView(R.layout.activity_main));

        setCalcViewModel();
        setMainViewModel();
        setTime();

        setOnClick();
    }

    @Override
    protected void setBindingView(@NonNull View view) {
        setContentsView(view, ActivityMainBinding.bind(view));
    }

    @Override
    public void setCalcViewModel() {
        calcViewModel = new ViewModelProvider(this).get(CalcViewModel.class);
        calcViewModel.getCalcModel().observe(this, calcModel -> {
            xml.tvInputData.setText(calcModel.getInputData());
            xml.tvResult.setText(calcModel.getOutputData());
        });
    }

    @Override
    public void setMainViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getMainModel().observe(this, mainModel -> {
            xml.tvTime.setText(mainModel.getNowTime());
        });
    }

    @Override
    public void setTime() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    runOnUiThread(() -> mainViewModel.setTime());
                    try {
                        Thread.sleep(TIME_TICK);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    private void setOnClick() {
        xml.btnNum0.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num1)));
        xml.btnNum1.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num1)));
        xml.btnNum2.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num2)));
        xml.btnNum3.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num3)));
        xml.btnNum4.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num4)));
        xml.btnNum5.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num5)));
        xml.btnNum6.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num6)));
        xml.btnNum7.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num7)));
        xml.btnNum8.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num8)));
        xml.btnNum9.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.num9)));
        xml.btnPlus.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.plus)));
        xml.btnMinus.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.minus)));
        xml.btnMultiplication.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.multiplication)));
        xml.btnDivision.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.division)));
        xml.btnDelete.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.delete)));
        xml.btnResult.setOnClickListener(v -> calcViewModel.setCalc(mActivity.getString(R.string.result)));
        xml.btnUsage.setOnClickListener(v -> {
            //viewmodel은 view를 몰라야 하는데, 아래 코드를 viewmodel에 넣어버리면 UsageActivity라는 뷰를 알게 된다.
            Intent intent = new Intent(mActivity, UsageActivity.class);
            intent.putExtra("CalcModel",calcViewModel.getCalcModel().getValue());
            startActivity(intent);
        });
    }
}
