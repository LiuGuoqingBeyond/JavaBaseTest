package com.uppayplugin.unionpay.javabasetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetest.Interface.CheckedTextViewInterface;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.entity.ChoseCheckedTextView;

import java.util.List;

/**
 * User: LiuGq
 * Date: 2018/6/21
 * Time: 16:47
 */

public class MyListViewAdapter extends BaseAdapter{
    private final List<String> banks;
    private final CheckedTextViewInterface checkedTextViewInterface;
    private Context context;

    public MyListViewAdapter(Context context, List<String> banks, CheckedTextViewInterface checkedTextViewInterface){
        this.banks = banks;
        this.context = context;
        this.checkedTextViewInterface = checkedTextViewInterface;
    }
    @Override
    public int getCount() {
        return banks.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row, null);
        final CheckedTextView ctv = view.findViewById(R.id.list_row_ctv);
        TextView textView = view.findViewById(R.id.tvText);
        textView.setText(banks.get(position));
        ctv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ctv.toggle();// 反转
                if (ctv.isChecked()){
                    checkedTextViewInterface.getisCheckedMessage(banks.get(position));
                }
            }
        });

        return view;
    }
}
