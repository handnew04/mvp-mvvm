package com.example.mvvmsample.Usage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmsample.Calculator.CalcModel;
import com.example.mvvmsample.R;
import com.example.mvvmsample.databinding.RvItemUsageBinding;

import java.util.ArrayList;


/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * CalcMain Activity
 * */
class UsageAdapter extends RecyclerView.Adapter<UsageAdapter.UsageViewHolder> {
    private Context mContext;
    private ArrayList<CalcModel.CalcResult> mList = new ArrayList<>();

    @NonNull
    @Override
    public UsageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.rv_item_usage, parent, false);

        UsageViewHolder usageViewHolder = new UsageViewHolder(view);

        return usageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsageViewHolder holder, int position) {
        if (mList == null || mList.size() == 0) {
            return;
        }

        CalcModel.CalcResult item = mList.get(position);
        if (item != null) {
            holder.xml.tvExpr.setText(item.getExpr());
            holder.xml.tvResult.setText(item.getResult());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItem(ArrayList<CalcModel.CalcResult> list) {
        this.mList.addAll(list);
    }

    static class UsageViewHolder extends RecyclerView.ViewHolder {
        RvItemUsageBinding xml;
        public UsageViewHolder(@NonNull View itemView) {
            super(itemView);
            xml = RvItemUsageBinding.bind(itemView);
        }
    }
}

