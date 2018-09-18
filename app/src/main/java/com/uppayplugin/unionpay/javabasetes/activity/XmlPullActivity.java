package com.uppayplugin.unionpay.javabasetes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity;
import com.uppayplugin.unionpay.javabasetes.bean.Student;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XmlPullActivity extends ToolBarActivity {
    @BindView(R.id.btn_xml)
    Button btnXml;
    private List<Student> students;
    private Student student;
    private InputStream is;

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_xml_pull;
    }

    @Override
    protected void initViewsAndEvents() {
        ButterKnife.bind(this);
        btnXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xmlPull();
            }
        });
    }

    private void xmlPull() {
        //得到XML解析器
        try {
            is = getAssets().open("Area1.xml");
            getProvinceFromXml(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    public void getStudentsFromXMl(InputStream stream) throws Exception {
        XmlPullParser parser = Xml.newPullParser();//获取pull解析器
        parser.setInput(stream, "utf-8");//设置输入流的编码方式
        int eventType = parser.getEventType();//得到第一个事件类型
        while (eventType != XmlPullParser.END_DOCUMENT) {//直到文档结束一直循环处理
            if (eventType == XmlPullParser.START_DOCUMENT) {//文档开始
                students = new ArrayList<>();
            } else if (eventType == XmlPullParser.START_TAG) {//标签开始
                String tagName = parser.getName();//获取标签名称
                if (tagName != null) {
                    if (tagName.equals("option")) {
                        student = new Student();
                        student.setId(parser.getAttributeValue(0));
                    } else if (tagName.equals("option")) {
                        student.setName(parser.nextText());
                    } else if (tagName.equals("age")) {
                        student.setOption(parser.nextText());
                    }
                }
            } else if (eventType == XmlPullParser.END_TAG) {//标签结束
                String tagName = parser.getName();
                if (tagName != null && tagName.equals("select")) {
                    students.add(student);
                    student = null;
                }
            }

            eventType = parser.next();
//			Log.e("msg", "eventType="+eventType);

        }
        if (students != null) {
            for (int i = 0; i < students.size(); i++) {
                Log.e("msg", "student [id=" + students.get(i).getId() + ",  name=" + students.get(i).getName() + ",  age=" + students.get(i).getOption() + "]");
            }
        }

    }
    public static String[] getProvinceFromXml(InputStream is) throws Exception
    {
        String[] allProvince = new String[64];
        int realCount = 0;
        XmlPullParser parse = Xml.newPullParser();
        parse.setInput(is, "utf-8");
        int event = parse.getEventType();
        while (event != XmlPullParser.END_DOCUMENT)
        {
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    Logger.d(""+ "文件解析开始");
                    break;
                case XmlPullParser.START_TAG:
                    if ("Province".equals(parse.getName()))
                    {
                        String provinceName = parse.getAttributeValue(2);
                        allProvince[realCount] = provinceName;
                        realCount++;
                        // Log.d(tag, "provinceName:" + provinceName);
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if ("Province".equals(parse.getName()))
                    {

                    }
                    break;

                default:
                    break;
            }

            event = parse.next();
        }
        Logger.d(""+ "结束解析");
        // return item;
        if (realCount > 0) {
            String[] targetProvinces = new String[realCount];
            System.arraycopy(allProvince, 0, targetProvinces, 0, realCount);
            return targetProvinces;
        }
        else{
            return null;
        }

    }

}
