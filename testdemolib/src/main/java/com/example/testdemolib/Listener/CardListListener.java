package com.example.testdemolib.Listener;

import com.example.testdemolib.entity.respons.QueryCardListResponseModel;

/**
 * Created by LiuGuoqing on 2018/7/18 0018 14:30
 * E-Mail Address：1076790023@qq.com
 */

public interface CardListListener {
    void _onNext(QueryCardListResponseModel queryCardListResponseModel);
    void _onError(String error);
}
