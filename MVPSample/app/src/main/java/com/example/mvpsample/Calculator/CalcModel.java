package com.example.mvpsample.Calculator;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * Calc Model
 * */
@Data @NoArgsConstructor @AllArgsConstructor
public class CalcModel implements Parcelable {
    private String inputData = "";               //input  data
    private String outputData = "";              //result data
    private CalcResultList calcResultList = new CalcResultList(); //result data usage

    public void addUsage(String expr, String result) {
        calcResultList.resultUsage.add(new CalcResult(expr,result));
    }

    private CalcModel(Parcel in) {
        inputData = in.readString();
        outputData = in.readString();
        calcResultList = in.readParcelable(CalcResultList.class.getClassLoader());
    }

    public static final Creator<CalcModel> CREATOR = new Creator<CalcModel>() {
        @Override
        public CalcModel createFromParcel(Parcel in) {
            return new CalcModel(in);
        }

        @Override
        public CalcModel[] newArray(int size) {
            return new CalcModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(inputData);
        dest.writeString(outputData);
        dest.writeParcelable(calcResultList, flags);
    }


    @Data @NoArgsConstructor @AllArgsConstructor
    public static class CalcResultList implements Parcelable{
        private ArrayList<CalcResult> resultUsage = new ArrayList<>();

        CalcResultList(Parcel in) {
            in.readTypedList(resultUsage, CalcResult.CREATOR);
        }

        public static final Creator<CalcResultList> CREATOR = new Creator<CalcResultList>() {
            @Override
            public CalcResultList createFromParcel(Parcel in) {
                return new CalcResultList(in);
            }

            @Override
            public CalcResultList[] newArray(int size) {
                return new CalcResultList[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(resultUsage);
        }
    }


    @Data @ToString
    public static class CalcResult implements Parcelable {
        String expr;
        String result;
        CalcResult(String expr, String result) {
            this.expr = expr;
            this.result = result;
        }

        private CalcResult(Parcel in) {
            expr = in.readString();
            result = in.readString();
        }

        public static final Creator<CalcResult> CREATOR = new Creator<CalcResult>() {
            @Override
            public CalcResult createFromParcel(Parcel in) {
                return new CalcResult(in);
            }

            @Override
            public CalcResult[] newArray(int size) {
                return new CalcResult[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(expr);
            dest.writeString(result);
        }
    }

}