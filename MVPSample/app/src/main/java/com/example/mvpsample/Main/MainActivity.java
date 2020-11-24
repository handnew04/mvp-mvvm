package com.example.mvpsample.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvpsample.Base.BaseActivity;
import com.example.mvpsample.R;
import com.example.mvpsample.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainView {
    private final String TAG = MainActivity.class.getSimpleName();
    private final int TIME_TICK = 1000;

    // 1:1로 연결하기 위해
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(getView(R.layout.activity_main));

        mainPresenter = new MainPresenter(this);
        setOnClick();
  //      setTimer();

    }

    @Override
    protected void setBindingView(@NonNull View view) {
        setContentsView(view, ActivityMainBinding.bind(view));
    }

    @Override
    public void setInputData(String inputData) {
        //view가 최종적으로 그릴지 말지를 결정한다.
        xml.tvInputData.setText(inputData);
    }

    @Override
    public void setResultData(String resultData) {
        Log.d(TAG,">>>>>"+resultData);
        xml.tvResult.setText(resultData);
    }

//    @Override
//    public void setTime(String time) {
//        xml.tvTime.setText(time);
//    }

    private void setOnClick() {
        xml.btnNum0.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num0)));
        xml.btnNum1.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num1)));
        xml.btnNum2.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num2)));
        xml.btnNum3.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num3)));
        xml.btnNum4.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num4)));
        xml.btnNum5.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num5)));
        xml.btnNum6.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num6)));
        xml.btnNum7.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num7)));
        xml.btnNum8.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num8)));
        xml.btnNum9.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.num9)));
        xml.btnPlus.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.plus)));
        xml.btnMinus.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.minus)));
        xml.btnMultiplication.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.multiplication)));
        xml.btnDivision.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.division)));
        xml.btnDelete.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.delete)));
        xml.btnResult.setOnClickListener(v -> mainPresenter.onItemClick(mActivity.getString(R.string.result)));
        xml.btnUsage.setOnClickListener(v -> mainPresenter.onStartActivity(mActivity));
    }

//    private void setTimer() {
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                while (!isInterrupted()) {
//                    runOnUiThread(() -> mainPresenter.getTime());
//                    try {
//                        Thread.sleep(TIME_TICK);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();
//    }
}
