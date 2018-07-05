package com.uppayplugin.unionpay.javabasetest.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.uppayplugin.unionpay.javabasetest.Interface.GetInter;
import com.uppayplugin.unionpay.javabasetest.Interface.TestEightListener;
import com.uppayplugin.unionpay.javabasetest.Interface.TestInter;
import com.uppayplugin.unionpay.javabasetest.Interface.TestInterface;
import com.uppayplugin.unionpay.javabasetest.Interface.TextSevenListener;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.config.Constant;
import com.uppayplugin.unionpay.javabasetest.utils.JSONUtil;
import com.uppayplugin.unionpay.javabasetest.utils.PayUtils;
import com.uppayplugin.unionpay.javabasetest.utils.net.HTTPSRequestUtils;
import com.uppayplugin.unionpay.javabasetest.utils.net.NetUtil;
import com.uppayplugin.unionpay.javabasetest.utils.personal.CircleImageView;
import com.uppayplugin.unionpay.javabasetest.utils.personal.FileUtil;
import com.uppayplugin.unionpay.libcommon.rsa.RSACoder;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.uppayplugin.unionpay.javabasetest.config.Constant.privateKey;


public class MainActivity extends BaseActivity {

    private Button btnText;
    private WebView webView;
    private ImageView imgView;
    private TextView tvText;
    private static final int REQUEST_BRANCH = 105;
    private GetInter testInterface;
    private String time;

    // 头像图片路径
    private String imgPath = "";
    private Context mContext = MainActivity.this;
    private CircleImageView ivHeadImg;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;
    private PopupWindow popupWindow;
    private TextView toolbarTitle;
    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        imgView = findViewById(R.id.imgView);
        ivHeadImg = findViewById(R.id.iv_head_img);
        tvText = findViewById(R.id.tvText);
        btnText = findViewById(R.id.btn_text);
//        toolbarTitle = findViewById(R.id.toolbar_title);
//        toolbarTitle.setText("标题");
//        LinearLayout toolbarLayout = findViewById(R.id.toolbarLayout);
//        Log.d("生命周期", "onCreate()");
//        setImmerseLayout(toolbarLayout);
//        full(true);
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(v);
            }
        });
    }

    @Override
    protected void findView() {

    }

    protected void setImmerseLayout(View view) {// view为标题栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initData(View view) {
//        String str = "123456789";
//        Toast.makeText(mContext,str.contains("568")+"",Toast.LENGTH_LONG).show();//包含，字符串必须连着？
//        Toast.makeText(mContext,str.charAt(3)+"",Toast.LENGTH_LONG).show();//读取第几个数值

        //删除指定位置/多个,插入字符串
//        StringBuffer str = new StringBuffer("123456789");
//        Toast.makeText(mContext,str.deleteCharAt(3)+"",Toast.LENGTH_LONG).show();
//        Toast.makeText(mContext,str.delete(1,3)+"",Toast.LENGTH_LONG).show();
//        Toast.makeText(mContext,str.insert(1,"xyz")+"",Toast.LENGTH_LONG).show();

        //以指定字符串作为分隔符，对当前字符串进行分割，分割的结果是一个数组
//        String str = "wei_xue_yuan_is_good";
//        String strArr[] = str.split("_");
//        Toast.makeText(mContext, Arrays.toString(strArr)+"",Toast.LENGTH_LONG).show();

        //二维数组
//        int[][] c = new int[][]{{1,2},{3,4}};
//        for (int i = 0; i < c.length; i++) {
//            for (int j = 0; j < c[i].length; j++) {
//                Toast.makeText(mContext, "a["+ j + "][" + j + "]=" + c[i][j] + ", "+"",Toast.LENGTH_LONG).show();
//            }
//        }
//        for (int[] a : c){
//            for (int b : a){
//                Toast.makeText(mContext, b+"",Toast.LENGTH_LONG).show();
//            }
//        }

        //冒泡排序
//        int[] source = { 5, 8, 1, 7, 6, 19, 10, 3 };
        //冒泡排序
//        bubbleSort(source);
        //选择排序
//        selectSort(source);

        //关于单例模式
//        Singleton.getInstance().toast(mContext,"这个是单例模式");

        //关于单例

        //list、map、set接口实例
//        List list = new ArrayList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.remove(0);
//        Log.d("list",list.size()+"");

        //迭代器
//        Iterator iterator = list.iterator();//list.iterator 获取迭代器对象
//        while (iterator.hasNext()){//判断后面是否还有元素
//            Log.d("遍历出来的string=", iterator.next()+"");//.next()获得下一个元素
//        }

        //关于通知栏权限
//        if (isNotificationEnabled(mContext)){
//            Toast.makeText(mContext, isNotificationEnabled(mContext)+"",Toast.LENGTH_LONG).show();
//        }else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
//                // 进入设置系统应用权限界面
//                Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                startActivity(intent);
//                return;
//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
//                // 进入设置系统应用权限界面
//                Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                startActivity(intent);
//                return;
//            }
//        }

        //加载assets下的html文件，并且与网页有关的css，js，图片等文件也会的加载。
//        webView.loadUrl("file:///android_asset/SigningAgreements.html");
//        webView.loadUrl("file:///android_asset/sinopayonline.html");//加载html网页文件
        //加载assets下的图片资源
//        String path = "guide_350_02.png";
//        try {
//            InputStream is = mContext.getAssets().open(path);
//            Bitmap bitmap = BitmapFactory.decodeStream(is);
//            imgView.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //加载assets下的文本文件
//        String fileName = "各大应用市场网站账号及密码";
//        try {
//            InputStream is = getAssets().open(fileName+".txt");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            String text = new String(buffer, "GBK");
//            tvText.setText(text);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //枚举
//        TestEnum testEnum = TestEnum.ONE;
//        switch (testEnum){
//            case ONE:
//                Toast.makeText(mContext,testEnum+"",Toast.LENGTH_LONG).show();
//                break;
//        }

        //关于线程操作
        // a)	创建一个自己的线程类，继承Thread，重写run方法
        // b)	创建一个自己的任务类，实现Runnable接口，重写run方法
//        for (int i = 0; i < 1000; i++) {
//            Log.d("主线程打印的结果",i+"");
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10000; i++) {
//                    Log.d("子线程打印的结果",i+"");
//                }
//            }
//        }).start();

//        new A().start();
//        new Thread(new B()).start();

        //创建文件/文件夹
//        String sdCardPath = FileUtils.getSDCardPath();
//        FileUtils fileUtils = new FileUtils();
//        fileUtils.createFile("hahahaha.text");
//        Toast.makeText(mContext, "创建文件/文件夹" + sdCardPath + "成功", Toast.LENGTH_LONG).show();
        //找不到文件名，待查！！


        //忆单例
//        SingletonTwo.getInstance().getNessage(mContext,"忆单例");
//        Animal.getInstance().eat();

        //for循环到某个值跳出循环
//        for (int i = 0; i < 100; i++) {
//            Log.d("总循环了", i + "");
//            if (i == 50) {
//                Log.d("循环到了", i + "");
//                break;
//            }
//        }

        //创建数组:固定数组个数
//        int[] arr = new int[2];
//        arr[0] = 1;
//        arr[1] = 2;
//        Toast.makeText(mContext,arr[0]+"",Toast.LENGTH_LONG).show();

        //忽略大小写比较
//        String A = "ABC";
//        if (A.equalsIgnoreCase("abc")){
//            Toast.makeText(mContext,"true",Toast.LENGTH_LONG).show();
//        }

        //实体
//        Test test = new Test();
//        test.setOne("1");
//        test.setTwo("2");
//        test.setThree("3");
//
//        Toast.makeText(mContext,test.getOne().toString(),Toast.LENGTH_LONG).show();

        //回调
//        Intent intent = new Intent(mContext,SecondActivity.class);
//        startActivityForResult(intent,REQUEST_BRANCH);

        //99
//        for (int i = 0; i <= 9; i++) {
//            for (int j = 0; j <= 9; j++) {
//                if (j<i){
//                    System.out.print("");
//                }else {
//                    System.out.printf("%d*%d=%2d  ", i, j, i*j);
//                }
//            }
//            System.out.print("\n");
//        }

        //继承
//        Mouse mouse = new Mouse("小白",18);
//        mouse.eat();

        //abstract+继承
//        Rectangle rectangle = new Rectangle();//矩形
//        rectangle.setLength(10);
//        rectangle.setWidth(5);
//        double rec_area = rectangle.area();
//        double rec_perimeter = rectangle.perimeter();
//        System.out.println("矩形的面积："+rec_area+"，周长"+rec_perimeter);
//
//        Triangle tri = new Triangle();
//        tri.setA(3);
//        tri.setB(4);
//        tri.setC(5);
//        double tri_area = tri.area();
//        double tri_perimeter = tri.perimeter();
//        System.out.println("三角形的面积："+tri_area+"，周长"+tri_perimeter);
//
//        Circle cir = new Circle();
//        cir.setDiameter(10);
//        double cir_area = cir.area();
//        double cir_perimeter = cir.perimeter();
//        System.out.println("圆形的面积："+cir_area+"，周长"+cir_perimeter);

        //忆回调
//        testInterface = new InterImpl();
//        testInterface.getMsg(mContext, "哈哈哈", anInterface);

        //foreach循环遍历
//        int[] arr = {1,2,3,4,5};
//        for (int a : arr){
//            Log.d("打印了遍历",a+"");
//        }

        //for循环遍历2
//        Child child = new Child(mContext,"小明",18);
//        child.getMessage();

        //修单例
//        Animal.getInstance().eat(mContext,"小白",18);

        //静态方法和非静态方法
        /**
         * 静态方法不可以访问非静态变量
         * 非静态方法可以访问静态变量
         */

        //装箱
//        Integer integer = new Integer(10);//利用构造方法
//        Integer integer = Integer.valueOf("10");//利用包装类中的静态方法

        //拆箱
//        int i = integer.intValue();//返回包装类对象integer对应的数据类型

        //自动装箱
//        Integer i = 10;//可以把一个基本数据类型赋值给包装类
        //自动拆箱
//        int i = new Integer(10);//把包装类对象直接赋值给基本数据类型
//        Log.d("integer",i+"");

        //
//        Log.d("全大写","abc".toUpperCase()+"");
//        Log.d("全小写","ABC".toLowerCase()+"");
//        Log.d("首尾空白字符去掉"," abc   ".trim()+"");
//        Log.d("从开始到结束的子字符串","abc".substring(1)+"");
//        Log.d("在原有字符串上添加字符串","abc".concat("def")+"");

        //迭代器的删除元素
//        List<String> list = new ArrayList();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            if ("b".equals(iterator.next())){
//                iterator.remove();
//                Log.d("list",list+"");
//            }
//        }

        //接口-->jar-->调用，已完成的JavaBaselib项目

        //异常处理
//        try{
//            int a = 1/0;
//        }catch (Exception error){
//            Log.e("异常信息：",error+"");
//        }

        //流的处理-->照片
//        ivHeadImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 选取个人头像信息
//                uploadHeadImage();
//            }
//        });

        //忆回调
//        GetInterface getInterface = new TestImpl();
//        getInterface.getMessage(MainActivity.this,"接收到消息了吗？",testInter);

        //忆单例
//        SingletonThree.getInstance().testSingleton(MainActivity.this,"这个是单例");

        //忆99
//        for (int i = 0; i <= 9; i++) {
//            for (int j = 0; j <= 9; j++) {
//                if (j<i){
//                    System.out.print("");
//                }else {
//                    System.out.printf("%d * %d=%2d   ",i,j,i*j);
//                }
//            }
//            System.out.print("\n");
//        }

        //生命周期
//        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//        startActivity(intent);

        //fragment
//        TestFragment testFragment = new TestFragment();
//        FragmentManager mFragmentManager = getFragmentManager();//创建布局管理器
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();//创建一个事务
//        //add：往碎片集合中添加一个碎片；
//        //replace：移除之前所有的碎片，替换新的碎片（remove和add的集合体）(很少用，不推荐，因为是重新加载，所以消耗流量)
//        //参数：1.公共父容器的的id  2.fragment的碎片
//
//        fragmentTransaction.add(R.id.framelayout, testFragment);
//        fragmentTransaction.addToBackStack(null);
//        //提交事务
//        fragmentTransaction.commit();


        //这里可以通过点击事件跳转到对应的fragment上
//        @Override
//        public void onClick(View v) {
//            FragmentTransaction fragmentTransaction1 = mFragmentManager.beginTransaction();
//            switch (v.getId()) {
//                case R.id.frist:
//                    //判断fragmentOne是否已经存在
//                    if (mFragmentOne.isAdded()) {
//                        //如果fragmentOne已经存在，则隐藏当前的fragment，
//                        //然后显示fragmentOne（不会重新初始化，只是加载之前隐藏的fragment）
//                        fragmentTransaction1.hide(fragmentNow).show(mFragmentOne);
//                    } else {
//                        //如果fragmentOne不存在，则隐藏当前的fragment，
//                        //然后添加fragmentOne（此时是初始化）
//                        fragmentTransaction1.hide(fragmentNow).add(R.id.framelayout, mFragmentOne);
//                        fragmentTransaction1.addToBackStack(null);
//
//                    }
//                    fragmentNow = mFragmentOne;
//                    fragmentTransaction1.commit();
//                    break;
//                case R.id.two:
//                    if (mFragmentTwo.isAdded()) {
//                        fragmentTransaction1.hide(fragmentNow).show(mFragmentTwo);
//                    } else {
//                        fragmentTransaction1.hide(fragmentNow).add(R.id.framelayout, mFragmentTwo);
//                        fragmentTransaction1.addToBackStack(null);
//                    }
//                    fragmentNow = mFragmentTwo;
//                    fragmentTransaction1.commit();
//                    break;
//                case R.id.back_btn:
//                    //在上面给事务对象添加addToBackStack(null)，
//                    //下面就可以通过碎片管理对象（mFragmentManager）调用popBackStack()方法来返回上一个碎片（此时碎片管理器只有两个碎片）
//                    //因为我们是通过add的方法添加fragment的，而且只是添加的两次，其余都是显示和隐藏来实现
//                    //又因为我们当前占了一个fragment，所以我们只能回退一次，第二次回退就会是空的fragment（什么都没有）
//                    mFragmentManager.popBackStack();
//                    break;
//            }
//        }


        //四种启动模式standard、singleTop、singleTash、singleInstance
        //隐式跳转
//        Intent intent = new Intent(".activity.ClipImageActivity");
//        startActivity(intent);

        //Intent设置Flag
//        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);

        //跳转到播放音频
//        Intent intent = new Intent("android.intent.action.VIEW");
//        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/a.mp3"));
//        intent.setDataAndType(uri,"audio/*");
//        startActivity(intent);

        //启动模式
//        Intent intent = new Intent(MainActivity.this, Aactivity.class);
//        startActivity(intent);

        //startForResult()
//        Intent intent = new Intent(MainActivity.this,Aactivity.class);
//        startActivityForResult(intent,5);

        //选中一个，另外一个
//        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//        startActivity(intent);

        //金额选择
//        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//        startActivity(intent);

        //忆回调
//        TestTwoListener testTwoListener = new TestTwoListener() {
//            @Override
//            public void getMessage(Context context, String message) {
//                Toast.makeText(context,message,Toast.LENGTH_LONG).show();
//            }
//        };
//        testTwoListener.getMessage(MainActivity.this,"收到了吗");

        //忆单例
//        SingletonFour.getInstance().getMessage(MainActivity.this,"这个是单例");

        //handler
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }).start();

        //listener
//        testThreeListener.getMessage(MainActivity.this,"接收到了吗？");

        //点击创建window
//        View contentView = getLayoutInflater().inflate(R.layout.pop_wind_layout,null);
//        ImageView fanhuImageView = contentView.findViewById(R.id.fanhui_im);
//        fanhuImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        //创建一个windou对象
//        //参数一:弹出框的内容，需要用布局加载器加载布局文件
//        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setAnimationStyle(R.style.animTranslate);//设置出现和消失的动画
//        popupWindow.setFocusable(true);//点击返回可聚焦消失
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.RED));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            popupWindow.showAsDropDown(view,Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
//        }

        //接口回调
        /*TestFourImpl testFour = new TestFourImpl();
        testFour.getMessage(MainActivity.this,"这个是信息");*/

        //接口调用及回调 UPOPay
        /*TestFiveInter testFiveInter = new TestFiveImpl();
        testFiveInter.getMsg(MainActivity.this,testFiveListener);*/

        //TestSixInterface
        /*TestSixInter testSixInter = new TestSixImpl();
        testSixInter.getMsge(MainActivity.this,testSixListener);*/

        //Service
        /*Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);*/

        //webView 顶ScrollBar
        /*Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
        startActivity(intent);*/

        //语音合成
        /*SpeechUtility.createUtility(MainActivity.this, "appid=" + "5b0262ad");
        SpeechTTSUtils.getInstance(MainActivity.this).initTTS("有声音吗？", LanguageTypeEnum.CHINESE);*/

        //忆单例回调
        /*TestSevenInter testSevenInter = new TestSevenImpl();
        testSevenInter.sendMessage(MainActivity.this,textSevenListener);*/

        //沉浸式
        /*Intent intent = new Intent(MainActivity.this,Bactivity.class);
        startActivity(intent);*/

        //判断金额大小
        /*if (Double.valueOf("4999.99999")<5000.0){
            Toast.makeText(MainActivity.this, "小于5000", Toast.LENGTH_SHORT).show();
        }*/

        //字符串转数组
        /*List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        String[] s = new String[list.size()];
        s = list.toArray(s);

        for (int i = 0; i < s.length; i++) {
            Log.d("arrayList=",s[i]+"");
        }*/

        //CheckedTextView文字fuxuan复选框(单选/多选/没有框)
        /*Intent intent = new Intent(MainActivity.this,CheckedTextViewActivity.class);
        startActivity(intent);*/
        /*Intent intent2 = new Intent(MainActivity.this,CheckedTextViewTwoActivity.class);
        startActivity(intent2);*/

        //时间轮播
        /*Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(2010, 0, 1);
        pvTime = new TimePickerView.Builder(MainActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(date);
                time = str.substring(0, 7);
                Toast.makeText(mContext, time+"", Toast.LENGTH_SHORT).show();
            }
        }).setType(new boolean[]{true, true, false, false, false, false})
                .setSubmitText("ok")
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setRangDate(startDate, endDate)
                .setDate(endDate)
                .build();
        pvTime.show();*/

        //一个app内嵌另一app
        /*if(isAppInstallen(MainActivity.this,"com.zhongfu.upop")) {
            // 手机安装了应用
            String IMEI = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            for (int i = 0; i < 4; i++) {
                IMEI += Math.round(Math.random() * 9);
            }

            PreferencesUtil preferencesUtil = new PreferencesUtil(MainActivity.this);
            SharedInfo.mUserPwd = preferencesUtil.readPrefs(SharedInfo.PASSWORD);

            Intent intent = new Intent(Intent.ACTION_MAIN);
            //前提：知道要跳转应用的包名、类名
            ComponentName componentName = new ComponentName("com.zhongfu.upop", "com.zhongfu.upop.activity.LoginActivity");
            Bundle bundle = new Bundle();
            bundle.putString("xzf_mUserName", "17665232288");
            bundle.putString("xzf_mUserPwd", "123456");
            bundle.putString("xzf_mPhoneIEMI", "" + IMEI);
            intent.putExtras(bundle);
            intent.setComponent(componentName);
            MainActivity.this.startActivity(intent);
        } else {
            // 手机没有安装应用
            String apkPath = "file:///android_asset/unionpay.apk";
//            String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"unionpay.apk";
            if (!TextUtils.isEmpty(apkPath)) {
                SystemManager.setPermission(apkPath);//提升读写权限,否则可能出现解析异常
                SystemManager.install(MainActivity.this, apkPath, false);
            } else {
                Log.e("DownloadFinishReceiver", "apkPath is null");
            }
        }*/

        //设置透明背景
        /*Intent intent = new Intent(MainActivity.this,HyalinizeActivity.class);
        startActivity(intent);*/

        //RxJava链式编程
        /*initData();*/

        //扫码
//        openActivity(CaptureActivity.class);

        //



















    }
    @Override
    protected void initData() {
        if (NetUtil.checkNet(this)) {
            try {
                Map<String, String> map = new TreeMap<>();
                map.put("txnType", "35");
                String str = PayUtils.joinMapValue(map, '&');
                map.put("signature", RSACoder.sign(str.getBytes(), privateKey).replaceAll("\n\r", ""));
                String result = mGson.toJson(map);
                Logger.e("result" + result);

                HTTPSRequestUtils.getJson(Constant.HTTP_PATH, result)
                        .compose(bindUntilEvent(ActivityEvent.DESTROY))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(s -> {
                            Logger.e("s" + s);
                            JSONObject jsonObject = new JSONObject(s);
                            Map<String, Object> jsonMap = JSONUtil.jsonToMap(jsonObject);
                            final String status = (jsonMap.get("status") != null ? jsonMap.get("status") : "").toString();
                            final String msg = (jsonMap.get("msg") != null ? jsonMap.get("msg") : "").toString();
                            if ("0".equals(status)) {
                                Logger.e("countryCodeList_save:" + s);
                            }
                            /*Intent intents = new Intent(WelcomeActivity.this, LoginActivity.class);
                            startActivity(intents);
                            finish();*/
                            //去检测更新
//                            checkUpdate();
                        }, error -> {
                            Logger.d(error.getMessage());
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void initView() {

    }

    TestEightListener testEightListener = new TestEightListener() {
        @Override
        public void getMessage(String message) {
            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void RequestMonth(String time) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    TextSevenListener textSevenListener = new TextSevenListener() {
        @Override
        public void getSevenMessage(Context context, String message) {
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
    };
    /*TestSixListener testSixListener = new TestSixListener() {
        @Override
        public void senMessage(Context context, String msaage) {
            Toast.makeText(context,msaage,Toast.LENGTH_LONG).show();
        }
    };*/

    /*TestFiveListener testFiveListener = new TestFiveListener() {
        @Override
        public void sendMsg(Context context, String message) {
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
        }
    };*/


//    TestThreeListener testThreeListener = new TestThreeListener() {
//        @Override
//        public void getMessage(Context context, String message) {
//            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
//        }
//    };


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.d("requestCode="+requestCode+"","resultCode="+resultCode+"");
//        if (requestCode == 5 && resultCode == 55){
//            Toast.makeText(MainActivity.this,data.getStringExtra("标识"),Toast.LENGTH_LONG).show();
//        }
//    }
//
    /*@Override
    protected void onStart() {
        super.onStart();
        Log.d("生命周期", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("生命周期", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("生命周期", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("生命周期", "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("生命周期", "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("生命周期", "onDestroy()");
    }*/

    TestInter testInter = new TestInter() {
        @Override
        public void onResult(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
    };

    private void uploadHeadImage() {
        //PopUpWindow子控件操作
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = view.findViewById(R.id.btn_camera);
        TextView btnPhoto = view.findViewById(R.id.btn_photo);
        TextView btnCancel = view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        //在原界面上绘制
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(parent, Gravity.BOTTOM, 0, 0);
        }
        //设置背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });


        //popupWindow各子控件的功能操作
        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if ((ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(mContext,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        ) {
                    //申请WRITE_EXTERNAL_STORAGE权限  READ_EXTERNAL_STORAGE
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);

                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }

    private void gotoPhoto() {
        Log.d("evan", "*****************open photo********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择照片"), REQUEST_PICK);
    }

    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.zhongfu.upop" + ".fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    //照相机及照片剪切回调
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        switch (requestCode) {
//            case REQUEST_CAPTURE: //调用系统相机返回
//                if (resultCode == RESULT_OK) {
//                    gotoClipActivity(Uri.fromFile(tempFile));
//                }
//                break;
//            case REQUEST_PICK:  //调用系统相册返回
//                if (resultCode == RESULT_OK) {
//                    Uri uri = intent.getData();
//                    gotoClipActivity(uri);
//                }
//                break;
//            case REQUEST_CROP_PHOTO:  //剪切图片返回
//                if (resultCode == RESULT_OK) {
//                    final Uri uri = intent.getData();
//                    if (uri == null) {
//                        return;
//                    }
//                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
//                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//                    ivHeadImg.setImageBitmap(bitMap);
//
//                    //此处后面可以将bitMap转为二进制上传后台网络
//                    uploadUserPicToWeb(bitMap);
//                }
//                break;
//        }
//    }

    private void uploadUserPicToWeb(Bitmap bitMap) {
        //处理图片上传至后台操作：图片-->byte数组-->String
//        if(null == bitMap) return;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // outputStream
//        bitMap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] appicon = baos.toByteArray();// 转为byte数组
//        String imagePath = Base64.encodeToString(appicon, Base64.DEFAULT);
//        imagePath = URLEncoder.encode(imagePath, "utf-8");
    }

    /**
     * 打开截图界面
     */
    private void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    /*private void initView() {
        //Glide
        GlideApp.with(mContext)
                .load(imgPath)
                .placeholder(R.mipmap.list_icon_head_portrait)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(ivHeadImg);

    }*/


    //监听回调函数
    TestInterface anInterface = new TestInterface() {
        @Override
        public void onResult(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
        }
    };

    /**
     * @param requestCode:用于与startActivityForResult中的requestCode中值进行比较判断，是以便确认返回的数据是从哪个Activity返回的
     * @param resultCode:是由子Activity通过其setResult()方法返回。适用于多个activity都返回数据时，来标识到底是哪一个activity返回的值。
     * @param data:一个Intent对象，带有返回的数据。可以通过data.getXxxExtra(                                        );方法来获取指定数据类型的数据
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_BRANCH:
//                    Log.d("接收", data.getStringExtra("key"));
//                    break;
//            }
//        }
//    }

    /**
     * 判断权限是否打开
     *
     * @param context
     * @return
     */
    private boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
     /* Context.APP_OPS_MANAGER */
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                appOpsClass = Class.forName(AppOpsManager.class.getName());
            }
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            Toast.makeText(mContext, "array is null", Toast.LENGTH_LONG).show();
        }
        boolean flag = true;//设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已然完成。
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            Log.e("冒泡排序：", arr[i] + (i == arr.length - 1 ? "" : ","));
        }
    }

    /**
     * 简单选择排序
     *
     * @param arr
     */
    public void selectSort(int[] arr) {
        System.out.println("交换之前：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        //选择排序的优化
        for (int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
            int k = i;
            for (int j = k + 1; j < arr.length; j++) {// 选最小的记录
                if (arr[j] < arr[k]) {
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if (i != k) {  //交换a[i]和a[k]
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
        System.out.println();
        System.out.println("交换后：");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    /**
     * 判断手机是否安装某个应用
     * @param context
     * @param packageName  应用包名
     * @return   true：安装，false：未安装
     * 该方法容易报错：java.lang.RuntimeException: Package manager has died
     */
    public static boolean isAppInstallen(Context context ,String packageName){
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            installed = false;
        }
        return  installed;
    }
}
