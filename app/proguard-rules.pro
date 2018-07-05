# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#代码混淆的压缩比例，值在0-7之间
-optimizationpasses 5
#混淆后类名都为小写
-dontusemixedcaseclassnames
#指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses
#指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
#不做预校验的操作
-dontpreverify
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn kotlin.**
#生成原类名和混淆后的类名的映射文件
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#指定混淆是采用的算法
-keepattributes *Annotation*,InnerClasses
#不混淆Annotation
-keepattributes Signature
#不混淆泛型
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------
#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------
-keep class com.unionpay.** { *; }
-keep class com.zhongfu.** { *; }
-keep class com.zhongfu.entity.** { *; }


#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------
#eventBus
#-keepattributes *Annotation*
#-keepclassmembers class ** {
#    @org.greenrobot.eventbus.Subscribe <methods>;
#}
#-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
#-keepattributes Signature-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#log4j
#-libraryjars log4j-1.2.17.jar
#-dontwarn org.apache.log4j.**
#-keep class  org.apache.log4j.** { *;}

#极光推送的相关混淆
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}

# bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#友盟统计
#-keepclassmembers class * {
#  public <init> (org.json.JSONObject);
#}
#-keep public class [您的应用包名].R$*{
#  public static final int *;
#}
#-keepclassmembers enum * {
#  public static **[] values(); public static ** valueOf(java.lang.String);
#}

#-libraryjars com.trello.rxlifecycle2:rxlifecycle-2.2.0.jar
#-libraryjars com.trello.rxlifecycle2:rxlifecycle-components-2.2.0.jar
#-libraryjars com.tbruyelle.rxpermissions2:rxpermissions-0.9.4@aar
#-libraryjars com.jakewharton.rxbinding2:rxbinding-2.0.0.jar
#-libraryjars com.jakewharton.rxbinding2:rxbinding-support-v4-2.0.0.jar
#-libraryjars com.jakewharton.rxbinding2:rxbinding-appcompat-v7-2.0.0.jar
#-libraryjars com.jakewharton.rxbinding2:rxbinding-design-2.0.0.jar
#-libraryjars com.android.support:recyclerview-v7-27.0.0.jar
#-libraryjars com.android.support:appcompat-v7-27.0.0.jar
#-libraryjars com.orhanobut:logger-2.1.1.jar
#-libraryjars com.android.support:cardview-v7-27.0.0.jar
#-libraryjars com.android.support:multidex-1.0.2.jar
#-libraryjars com.android.support.constraint:constraint-layout-1.0.2.jar
#-libraryjars com.timehop.stickyheadersrecyclerview:library:0.4.3@aar

#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------
#-keep class 你的类所在的包.** { *; }
#-keepclasseswithmembers class com.demo.login.bean.ui.MainActivity$JSInterface {
#      <methods>;
#}
#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------
-keep class com.zhongfu.api.** { *; }
-keep class com.zhongfu.controller.** { *; }
-keep class com.zhongfu.Interface.** { *; }

#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#----------------------------------极光-------------------------
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

#-dontwarn com.google.**
#-keep class com.google.gson.** {*;}
#-keep class com.google.protobuf.** {*;}