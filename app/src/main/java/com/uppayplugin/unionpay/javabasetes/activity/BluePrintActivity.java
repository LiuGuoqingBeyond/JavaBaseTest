package com.uppayplugin.unionpay.javabasetes.activity;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.gprinter.command.EscCommand;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.utils.blueprint.DeviceConnFactoryManager;
import com.uppayplugin.unionpay.javabasetes.utils.blueprint.PrinterCommand;
import com.uppayplugin.unionpay.javabasetes.utils.blueprint.ThreadFactoryBuilder;
import com.uppayplugin.unionpay.javabasetes.utils.blueprint.ThreadPool;
import com.uppayplugin.unionpay.javabasetes.utils.device.BlueToothManagerUtils;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.OneOrTwoBtnDialogUtil;
import com.uppayplugin.unionpay.javabasetes.utils.dialog.ToastUtils;

import java.util.Vector;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;
import static com.uppayplugin.unionpay.javabasetes.config.Constant.MESSAGE_UPDATE_PARAMETER;
import static com.uppayplugin.unionpay.javabasetes.utils.blueprint.DeviceConnFactoryManager.ACTION_QUERY_PRINTER_STATE;
import static com.uppayplugin.unionpay.javabasetes.utils.blueprint.DeviceConnFactoryManager.CONN_STATE_FAILED;

public class BluePrintActivity extends ToolBarActivity {
    public static final int BLUETOOTH_REQUEST_CODE = 100;
    private int id = 0;
    private ThreadPool threadPool;
    private int counts;
    /**
     * 连接状态断开
     */
    private static final int CONN_STATE_DISCONN = 0x007;
    /**
     * 使用打印机指令错误
     */
    private static final int PRINTER_COMMAND_ERROR = 0x008;


    /**
     * ESC查询打印机实时状态指令
     */
    private byte[] esc = {0x10, 0x04, 0x02};


    /**
     * TSC查询打印机状态指令
     */
    private byte[] tsc = {0x1b, '!', '?'};
    @Override
    protected void initToolBar() {
        mToolbar.setCenterTitle("小票");
        mToolbar.getRightText().setText(getString(R.string.blue_print));
        mToolbar.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BlueToothManagerUtils.queryMobileBluetoothState(mContext)){
                    if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] == null ||
                            !DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
                        ToastUtils.showLong(getString(R.string.str_cann_printer));
                        startActivityForResult(new Intent(BluePrintActivity.this, BluetoothDeviceList.class), BLUETOOTH_REQUEST_CODE);
                        return;
                    }
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.ESC) {
                                sendReceiptWithResponse();
                            } else {
                                mHandler.obtainMessage(PRINTER_COMMAND_ERROR).sendToTarget();
                            }
                        }
                    });
                }else {
                    OneOrTwoBtnDialogUtil.getDialogInstance().dialogToShow(mContext, "", getString(R.string.bluetooth_is_not_enabled), getString(R.string.text_cancel), getString(R.string.text_set), true, new OneOrTwoBtnDialogUtil.OnListener() {
                        @Override
                        public void listener(DialogInterface dialogInterface, int i) {
                            if (i == 2){
                                BlueToothManagerUtils.openMobileBluetooth(mContext);
                            }
                            dialogInterface.dismiss();
                        }
                    });
                }
            }
        });
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                //Usb连接断开、蓝牙连接断开广播
                case ACTION_USB_DEVICE_DETACHED:
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    mHandler.obtainMessage(CONN_STATE_DISCONN).sendToTarget();
                    break;
                case DeviceConnFactoryManager.ACTION_CONN_STATE:
                    int state = intent.getIntExtra(DeviceConnFactoryManager.STATE, -1);
                    int deviceId = intent.getIntExtra(DeviceConnFactoryManager.DEVICE_ID, -1);
                    switch (state) {
                        case DeviceConnFactoryManager.CONN_STATE_DISCONNECT:
                            if (id == deviceId) {
//                                tvConnState.setText(getString(R.string.str_conn_state_disconnect));
                            }
                            break;
                        case DeviceConnFactoryManager.CONN_STATE_CONNECTING:
//                            tvConnState.setText(getString(R.string.str_conn_state_connecting));
                            break;
                        case DeviceConnFactoryManager.CONN_STATE_CONNECTED:
//                            tvConnState.setText(getString(R.string.str_conn_state_connected) + "\n" + getConnDeviceInfo());
                            break;
                        case CONN_STATE_FAILED:
                            ToastUtils.showLong(getString(R.string.str_conn_fail));
//                            tvConnState.setText(getString(R.string.str_conn_state_disconnect));
                            break;
                        default:
                            break;
                    }
                    break;
                case ACTION_QUERY_PRINTER_STATE:
                    if(counts >0) {
                        sendContinuityPrint();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void sendContinuityPrint() {
        ThreadPool.getInstantiation().addTask(new Runnable() {
            @Override
            public void run() {
                if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null
                        && DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getConnState()) {
                    ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder("MainActivity_sendContinuity_Timer");
                    ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, threadFactoryBuilder);
                    scheduledExecutorService.schedule(threadFactoryBuilder.newThread(new Runnable() {
                        @Override
                        public void run() {
                            counts--;
                            if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].getCurrentPrinterCommand() == PrinterCommand.ESC) {
                                sendReceiptWithResponse();
                            }
                        }
                    }), 1000, TimeUnit.MILLISECONDS);
                }
            }
        });
    }

    /**
     * 发送票据
     */
    void sendReceiptWithResponse() {
        EscCommand esc = new EscCommand();
        esc.addInitializePrinter();
        esc.addPrintAndFeedLines((byte) 1);//距离为1
        //---------------------------------------------------------------------- 此处开始打印-------------------------------------------------
        // 设置打印居中
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);

        // 打印图片
        Bitmap b = BitmapFactory.decodeResource(getResources(),
                R.mipmap.pic_china_unionpay);
        esc.addRastBitImageWithMethod(b, 400, 0,0);

        // 设置为倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        // 打印文字
        esc.addText("银联扫码签购单\n");
        esc.addPrintAndLineFeed();

        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        // 设置打印左对齐
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        // 打印文字
        esc.addText("商户存根");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 8);
        esc.addText(" MERCHANT COPY");
        esc.addPrintAndLineFeed();
        // 打印文字
        esc.addText("--------------------------------\n");

        // 打印文字
        esc.addText("商户名称(MERCHANT NAME)\n");
        // 设置为倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        // 打印文字
        esc.addText("711-OK shop\n");

        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("商户编码(MID):   606034458122020\n");
        esc.addText("终端编码(TID):          00042000\n");
        esc.addText("操作员号(OPERATOR NO):        01\n");
        esc.addText("交易类型(TRANS TYPE):\n");
        // 设置为倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("云闪付收款\n");
        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("批次号(BATCH NO):         000007\n");
        esc.addText("凭证号(VOUCHER NO):       000074\n");
        esc.addText("授权码(AUTH NO):          053349\n");
        esc.addText("支付订单号:         032168110905\n");
        esc.addText("商户订单号:         032168110905\n");

        /* 打印一维条码 */
        // 打印文字
//        esc.addText("Print code128\n");
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
//        esc.addSelectPrintingPositionForHRICharacters(EscCommand.HRI_POSITION.BELOW);
        // 设置条码可识别字符位置在条码下方
        // 设置条码高度为60点
        esc.addSetBarcodeHeight((byte) 60);
        // 设置条码单元宽度为1
        esc.addSetBarcodeWidth((byte) 3);
        // 打印Code128码
        esc.addCODE128(esc.genCodeC("032168110905"));
        esc.addPrintAndLineFeed();

        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);
        esc.addText("日期/时间(DATE/TIME):\n");
        esc.addText("2018-10-19 10:15:42\n");
        esc.addText("交易参考号(PRE.NO): 181016402258\n");
        esc.addText("金额(AMOUNT):\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTB, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        // 打印文字
        esc.addText("   HKD 0.01\n");
        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("小费(TIPS):\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTB, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        // 打印文字
        esc.addText("   HKD 0.01\n");
        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("总计(TOTAL):\n");
        esc.addSelectPrintModes(EscCommand.FONT.FONTB, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.ON, EscCommand.ENABLE.OFF);
        // 打印文字
        esc.addText("   HKD 0.01\n");
        // 取消倍高倍宽
        esc.addSelectPrintModes(EscCommand.FONT.FONTA, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF, EscCommand.ENABLE.OFF);
        esc.addText("--------------------------------\n");
        esc.addText("标注/REFERENCE\n");
        esc.addText("TOKEN:62170**** **** ****2678\n");

		/* 打印繁体中文 需要打印机支持繁体字库 */
        /*String message = "佳博智匯票據打印機\n";
        esc.addText(message, "GB2312");
        esc.addPrintAndLineFeed();*/

		/* 绝对位置 具体详细信息请查看GP58编程手册 */
        /*esc.addText("张振飞");
        esc.addSetHorAndVerMotionUnits((byte) 7, (byte) 0);
        esc.addSetAbsolutePrintPosition((short) 6);
        esc.addText("是个");
        esc.addSetAbsolutePrintPosition((short) 10);
        esc.addText("大傻逼");
        esc.addPrintAndLineFeed();*/

		/* 打印一维条码 *//*
        // 打印文字
        esc.addText("Print code128\n");
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        esc.addSelectPrintingPositionForHRICharacters(EscCommand.HRI_POSITION.BELOW);
        // 设置条码可识别字符位置在条码下方
        // 设置条码高度为60点
        esc.addSetBarcodeHeight((byte) 60);
        // 设置条码单元宽度为1
        esc.addSetBarcodeWidth((byte) 3);
        // 打印Code128码
        esc.addCODE128(esc.genCodeB("SMARNET"));
        esc.addPrintAndLineFeed();*/

        /*// 设置打印左对齐
        esc.addSelectJustification(EscCommand.JUSTIFICATION.LEFT);

		*//*
         * QRCode命令打印 此命令只在支持QRCode命令打印的机型才能使用。 在不支持二维码指令打印的机型上，则需要发送二维条码图片
		 *//*
        // 打印文字
        esc.addText("Print QRcode\n");
        // 设置纠错等级
        esc.addSelectErrorCorrectionLevelForQRCode((byte) 0x31);
        // 设置qrcode模块大小
        esc.addSelectSizeOfModuleForQRCode((byte) 8);
        // 设置qrcode内容
        esc.addStoreQRCodeData("www.smarnet.cc");
        esc.addPrintQRCode();// 打印QRCode
        esc.addPrintAndLineFeed();*/

        // 设置打印居中
        esc.addSelectJustification(EscCommand.JUSTIFICATION.CENTER);
        //打印文字
        esc.addText("Completed!\r\n");

        // 开钱箱
//        esc.addGeneratePlus(LabelCommand.FOOT.F5, (byte) 255, (byte) 255);
        esc.addPrintAndFeedLines((byte) 2);
        // 加入查询打印机状态，打印完成后，此时会接收到GpCom.ACTION_DEVICE_STATUS广播
        esc.addQueryPrinterStatus();
        esc.addCutPaper();
        Vector<Byte> datas = esc.getCommand();
        // 发送数据
        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].sendDataImmediately(datas);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONN_STATE_DISCONN:
                    if (DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id] != null) {
                        DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].closePort(id);
                    }
                    break;
                case PRINTER_COMMAND_ERROR:
                    ToastUtils.showLong(getString(R.string.str_choice_printer_command));
                    break;
                case MESSAGE_UPDATE_PARAMETER:
                    String strIp = msg.getData().getString("Ip");
                    String strPort = msg.getData().getString("Port");
                    //初始化端口信息
                    new DeviceConnFactoryManager.Build()
                            //设置端口连接方式
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.WIFI)
                            //设置端口IP地址
                            .setIp(strIp)
                            //设置端口ID（主要用于连接多设备）
                            .setId(id)
                            //设置连接的热点端口号
                            .setPort(Integer.parseInt(strPort))
                            .build();
                    threadPool = ThreadPool.getInstantiation();
                    threadPool.addTask(new Runnable() {
                        @Override
                        public void run() {
                            DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DeviceConnFactoryManager.closeAllPort();
        if (threadPool != null) {
            threadPool.stopThreadPool();
        }
//        unregisterReceiver(receiver);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_blue_print;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case BLUETOOTH_REQUEST_CODE:
                    /*获取蓝牙mac地址*/
                    String macAddress = data.getStringExtra(BluetoothDeviceList.EXTRA_DEVICE_ADDRESS);
                    //初始化话DeviceConnFactoryManager
                    new DeviceConnFactoryManager.Build()
                            .setId(id)
                            //设置连接方式
                            .setConnMethod(DeviceConnFactoryManager.CONN_METHOD.BLUETOOTH)
                            //设置连接的蓝牙mac地址
                            .setMacAddress(macAddress)
                            .build();
                    //打开端口
                    DeviceConnFactoryManager.getDeviceConnFactoryManagers()[id].openPort();
                    break;
            }
        }
    }
}
