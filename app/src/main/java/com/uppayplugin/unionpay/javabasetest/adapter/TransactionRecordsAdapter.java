package com.uppayplugin.unionpay.javabasetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testdemolib.entity.respons.QrCodePayInfoResponseModel;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.libcommon.inter.OnRecyclerViewItemClickListener;
import com.uppayplugin.unionpay.libcommon.utils.TextUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/11/14
 * Time: 16:30
 */

public class TransactionRecordsAdapter extends BaseRecyclerAdapter<QrCodePayInfoResponseModel, TransactionRecordsAdapter.ViewHolder>
        implements StickyRecyclerHeadersAdapter<TransactionRecordsAdapter.HeaderHolder> {

    OnRecyclerViewItemClickListener<QrCodePayInfoResponseModel> mListener;

    public void setListener(OnRecyclerViewItemClickListener<QrCodePayInfoResponseModel> listener) {
        mListener = listener;
    }

    LinkedHashMap<String, HeaderModel> stickHeader = new LinkedHashMap<>();
    List<Integer> positions;

    public static class HeaderModel {
        public String date;
        public String orderNum;

        public HeaderModel(String date, String orderNum) {
            this.date = date;
            this.orderNum = orderNum;
        }
    }

    public void setStickHeader(LinkedHashMap<String, HeaderModel> stickHeader) {
        positions = null;
        this.stickHeader = stickHeader;
    }

    public LinkedHashMap<String, HeaderModel> getStickHeader() {
        return stickHeader;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cashier_listview_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    @Override
    public long getHeaderId(int position) {
        int titleIndex = -1;
        if (positions == null) {
            positions = new ArrayList<>();
            for (String s : stickHeader.keySet()) {
                positions.add(Integer.valueOf(s));
            }
            Collections.sort(positions);
        }

        for (int index : positions) {
            if (position >= index)
                titleIndex = index;
        }

        return titleIndex;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return new HeaderHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trade_record_header, viewGroup, false));
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewHolder, int position) {
        if (stickHeader != null) {
            int titleIndex = 0;
            for (int index : positions) {
                if (position >= index)
                    titleIndex = index;
            }

            viewHolder.mDate.setText(stickHeader.get(String.valueOf(titleIndex)).date);
            viewHolder.mOrderNum.setText(String.format(viewHolder.mContext.getResources()
                    .getString(R.string.transaction_item_header_order_num
                    ), stickHeader.get(String.valueOf(titleIndex)).orderNum));


        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;

        @BindView(R.id.ivConsumptionType)
        ImageView ivConsumptionType;//交易类型
        @BindView(R.id.tvInTradeOrderCashier)
        TextView tvInTradeOrderCashier;//商户名称
        @BindView(R.id.tvOrderAmount)
        TextView tvOrderAmount;//订单金额
        @BindView(R.id.tvDate)
        TextView tvDate;//交易时间
        @BindView(R.id.tvPayAmount)
        TextView tvPayAmount;//支付金额

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            // FIXME: 2017/12/21 LGq 更换了交易记录单个点击,原因:需要传时间和订单号去查询
            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onItemClick(itemView, getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        public void bindData(QrCodePayInfoResponseModel cashier) {

//            ivConsumptionType.setImageResource(CurrencyUtils.getInstance(mContext).getMCCImage(TextUtil.setText(cashier.getMerType(), "")));

            tvInTradeOrderCashier.setText(cashier.getMerName());
            String time = cashier.getPayTime();

            if (TextUtils.isEmpty(time)) {
                time = cashier.getTxnTime();
            }

            if (TextUtils.isEmpty(time)) {
                time = cashier.getOrderTime();
            }
            if (!TextUtils.isEmpty(time))
                // FIXME: 2017/12/20 LGq 更改时间格式
                tvDate.setText(time.substring(4, 6) + "-"
                        + time.substring(6, 8) + " "
                        + time.substring(8, 10) + ":"
                        + time.substring(10, 12));

//            tvPayAmount.setText("-" + CurrencyUtils.getInstance(mContext).getCurrencyText(cashier.getBillingCurr()) + " " + new BigDecimal(cashier.getBillingAmt()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY).toString());
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRecordHeaderDate)
        TextView mDate;

        @BindView(R.id.tvRecordHeaderOrderNum)
        TextView mOrderNum;

        Context mContext;

        public HeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
    }
}
