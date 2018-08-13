package com.uppayplugin.unionpay.javabasetes.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetes.R;

/**
 * User: LiuGq
 * Date: 2018/4/24
 * Time: 16:15
 */

public class TestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,null);
        TextView click1 = view.findViewById(R.id.click1);
        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"这个是Fragment",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
