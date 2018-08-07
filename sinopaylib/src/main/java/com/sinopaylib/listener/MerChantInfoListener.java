package com.sinopaylib.listener;
import com.sinopaylib.entity.respons.QueryMerchantRep;

/**
 * Created by Administrator on 2018/8/6 0006.
 */

public interface MerChantInfoListener {
    void _onNext(QueryMerchantRep queryMerchantRep);
    void _onError(String error);
}
