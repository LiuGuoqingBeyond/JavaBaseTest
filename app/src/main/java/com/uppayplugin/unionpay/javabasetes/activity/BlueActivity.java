package com.uppayplugin.unionpay.javabasetes.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.adapter.BlueAdapter;
import com.uppayplugin.unionpay.javabasetes.entity.response.BlueCountRepModel;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;
import com.whty.xzfpos.base.AppToolBarActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BlueActivity extends AppToolBarActivity {
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_stop)
    Button btnStop;
    @BindView(R.id.btn_discoverable)
    Button btnSearch;
    @BindView(R.id.btn_search)
    Button button4;
    @BindView(R.id.recyclerView)
    RecyclerView mRcyclerView;
    private BluetoothAdapter bluetoothAdapter;
    private BlueAdapter blueAdapter;
    private ArrayList<BlueCountRepModel> blueList;
    private BlueCountRepModel blueCountRepModel;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_blue;
    }

    @Override
    protected void initViewsAndEvents() {
        //获取蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        blueList = new ArrayList<>();
        blueAdapter = new BlueAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRcyclerView.setLayoutManager(manager);
        mRcyclerView.setAdapter(blueAdapter);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @OnClick({R.id.btn_start, R.id.btn_stop, R.id.btn_discoverable, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                bluetoothAdapter.enable();
                ToastUtils.showLong("蓝牙已打开");
                break;
            case R.id.btn_stop:
                bluetoothAdapter.disable();
                ToastUtils.showLong("蓝牙已关闭");
                break;
            case R.id.btn_discoverable:
                discoverableBluetooth();

                break;
            case R.id.btn_search:
                searchBluetooh();
                break;
        }
    }

    /**
     * 打开蓝牙的可检测性
     */
    private void discoverableBluetooth() {

        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intent, 0);
        ToastUtils.showLong("蓝牙可检测性已打开");
    }

    //搜索蓝牙
    private void searchBluetooh() {
        //添加蓝牙可以被发现
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        MBluetoothBroadCast mBluetoothBroadCast = new MBluetoothBroadCast();
        registerReceiver(mBluetoothBroadCast, intentFilter);

        IntentFilter intentFilter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothBroadCast, intentFilter1);

        //开始找蓝牙设备
        bluetoothAdapter.startDiscovery();
    }

    //符合我注册的过滤器 ,就可以进入onReceive方法
    class MBluetoothBroadCast extends BroadcastReceiver {
        //一旦被发现或链接,我们就能能收到广播;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //如果我们搜索到了其他的蓝牙,就会进入这个判断
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //得到搜索到的蓝牙设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    blueCountRepModel = new BlueCountRepModel();
                    //用设备获取蓝牙名字
                    blueCountRepModel.setBlueName(device.getName());
                    blueCountRepModel.setBlueAddress(device.getAddress());
                    blueList.add(blueCountRepModel);

                }
                //如果搜索完毕,会进入这个判断;
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                ToastUtils.showLong("已搜数完成");

            }else if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)){
                blueAdapter.appendToList(blueList);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
