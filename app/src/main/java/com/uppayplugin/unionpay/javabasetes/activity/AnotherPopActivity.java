package com.uppayplugin.unionpay.javabasetes.activity;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.GetData;

import java.util.List;

public class AnotherPopActivity extends ToolBarActivity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private PopupWindow popupWindow;
    private GetData getData;
    private List<String> data;
    private int popType;
    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_another_pop;
    }

    @Override
    protected void initViewsAndEvents() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        getData = new GetData();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                data = getData.GetData(GetData.TYPE_ONE);
                if (popupWindow != null && popType == 1) {
                    popupWindow = null;
                } else {
                    showPopupWindow(data);
                    popType = 1;
                }

                break;
            case R.id.btn2:
                data = getData.GetData(GetData.TYPE_TWE);
                if (popupWindow != null && popType == 2) {
                    popupWindow = null;
                } else {
                    showPopupWindow(data);
                    popType = 2;
                }
                break;
            case R.id.btn3:
                data = getData.GetData(GetData.TYPE_THREE);
                if (popupWindow != null && popType == 3) {
                    popupWindow = null;
                } else {
                    showPopupWindow(data);
                    popType = 3;
                }
                break;
        }
    }
    private void showPopupWindow(List<String> data) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuplayout, null);
        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);


        ListView listView = (ListView) contentView.findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(data));


        // 需要设置一下此参数，点击外边可消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        //PopupWindow是否具有获取焦点的能力,如果你不这样设置的话，那么popupwindow之外的控件是无法响应事件的。
        popupWindow.setFocusable(false);
        //设置popupwindow显示在btn1下方
        popupWindow.showAsDropDown(btn1);
    }


    //popupwindow中显示的是listview，这是listview的adapter
    private class MyAdapter extends BaseAdapter {
        private List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(AnotherPopActivity.this).inflate(R.layout.item, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_show);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(list.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AnotherPopActivity.this, "点击了" + position + "位置的数据", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            });
            return convertView;
        }
    }

    private class ViewHolder {
        TextView textView;
    }
}
