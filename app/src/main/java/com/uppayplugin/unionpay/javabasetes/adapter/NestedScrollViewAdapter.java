package com.uppayplugin.unionpay.javabasetes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.libcommon.inter.onItemClickListener;
import com.uppayplugin.unionpay.libcommon.inter.onItemLongClickListener;

/**
 * User: LiuGuoqing
 * Data: 2018/8/20 0020.
 */

public class NestedScrollViewAdapter extends BaseRecyclerAdapter<BankCardInfo, NestedScrollViewAdapter.ViewHolder> {

    onItemLongClickListener<BankCardInfo> mLongClickListener;
    onItemClickListener<BankCardInfo> mClickListener;

    public static final int ONE_ITEM = 1;
    public static final int TWO_ITEM = 2;

    public void setmClickListener(onItemClickListener<BankCardInfo> mClickListener) {
        this.mClickListener = mClickListener;
    }

    public void setmLongClickListener(onItemLongClickListener<BankCardInfo> mLongClickListener) {
        this.mLongClickListener = mLongClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.test_string_recycler_layout, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(getItem(position), position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTest;
        private final Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            tvTest = itemView.findViewById(R.id.tv_test);
            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {//长按
                @Override
                public boolean onLongClick(View view) {
                    mLongClickListener.onItemLongClick(getItem(getAdapterPosition()),getAdapterPosition());
                    return true;
                }
            });*/
            itemView.setOnClickListener(new View.OnClickListener() {//点击
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        public void bindData(BankCardInfo bankCardInfo, int position) {
            tvTest.setText(bankCardInfo.getBankName());
        }
    }
}
