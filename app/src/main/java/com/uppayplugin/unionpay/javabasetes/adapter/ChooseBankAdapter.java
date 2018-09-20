package com.uppayplugin.unionpay.javabasetes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.bean.BankBean;
import com.uppayplugin.unionpay.javabasetes.bean.BankTestBean;
import com.uppayplugin.unionpay.libcommon.inter.OnRecyclerViewItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: LiuGuoqing
 * Data: 2018/9/14 0014.
 */

public class ChooseBankAdapter extends BaseRecyclerAdapter<BankTestBean, ChooseBankAdapter.ViewHolder> {
    private OnRecyclerViewItemClickListener<BankTestBean> mListener;

    public void setmListener(OnRecyclerViewItemClickListener<BankTestBean> mListener) {
        this.mListener = mListener;
    }

    @Override
    public ChooseBankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseBankAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_list_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.BindData(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bankName)
        TextView tvBankName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (mListener != null) {
                    mListener.onItemClick(view, getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        public void BindData(BankTestBean bankTestBean) {
            tvBankName.setText(bankTestBean.getBankName());
        }
    }
}
