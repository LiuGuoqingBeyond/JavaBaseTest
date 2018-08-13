package com.uppayplugin.unionpay.javabasetes.utils.net;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.utils.exception.ZhongFuException;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by mrpanda on 4/5/17.
 */

public class HTTPSRequestUtils {
    private static Context ctx;

    private static OkHttpClient okHttpClient;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static Response response;

    public static Observable<String> getJson(final String path, final String json) throws ZhongFuException {

        //TODO 需要对关键位置进行异常处理。并判断具体位置错误。
        return Observable.just("")
                .map(s -> {
                    okHttpClient = new OkHttpClient.Builder()
                            .sslSocketFactory(createSSLSocketFactory())
                            .hostnameVerifier(new TrustAllHostnameVerifier())
//                            .sslSocketFactory(context.getSocketFactory())
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .build();

                    RequestBody requestBody = RequestBody.create(JSON, json);
                    Request request = new Request.Builder()
                            .url(path)
                            .post(requestBody)
                            .build();

                    try {
                        response = okHttpClient.newCall(request).execute();
                    } catch (Exception e) {
                        Logger.e("网络请求错误：", e.getMessage());
                    }

                    Logger.e("网络返回：" + "responseCode = " + response.code() + "\nresponseMessage = " + response.message() + " \n" + response.toString());

                    return response;
                })
                .retry(3)
                .map(response -> {
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        Logger.e("response", response.body().string());
                        throw new Exception(response.message());
                    }
                });
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, null);

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


}
