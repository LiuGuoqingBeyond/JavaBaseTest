package com.sinopaylib.listener;
import com.sinopaylib.entity.respons.LoginAppRepModel;

/**
 * Created by Administrator on 2018/7/30 0030.
 */

public interface AppLoginListener {
    void _onNext(LoginAppRepModel loginAppRepModel);
    void _onError(String error);
}
