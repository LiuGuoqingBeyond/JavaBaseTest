package com.uppayplugin.unionpay.javabasetes.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.entity.response.BlueCountRepModel;

/**
 * User: LiuGuoqing
 * Data: 2018/8/27 0027.
 */

public class BlueAdapter  extends BaseRecyclerAdapter<BlueCountRepModel, BlueAdapter.ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BlueAdapter.ViewHolder(View.inflate(parent.getContext(), R.layout.blue_item_layout, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(getItem(position), position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final TextView blueName,blueId;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            blueName = itemView.findViewById(R.id.blue_name);
            blueId = itemView.findViewById(R.id.blue_id);
        }

        public void bindData(BlueCountRepModel blueCountRepModel, int position) {
            blueName.setText(blueCountRepModel.getBlueName());
            blueId.setText(blueCountRepModel.getBlueAddress());
        }
    }
}
