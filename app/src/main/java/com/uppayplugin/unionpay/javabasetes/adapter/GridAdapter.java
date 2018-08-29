package com.uppayplugin.unionpay.javabasetes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.entity.response.GridRepModel;
import com.uppayplugin.unionpay.libcommon.inter.onItemClickListener;

/**
 * User: LiuGuoqing
 * Data: 2018/8/29 0029.
 */

public class GridAdapter extends BaseRecyclerAdapter<GridRepModel,GridAdapter.ViewHolder> {
    onItemClickListener<GridRepModel> mClickListener;

    public void setmClickListener(onItemClickListener<GridRepModel> mClickListener) {
        this.mClickListener = mClickListener;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridAdapter.ViewHolder(View.inflate(parent.getContext(), R.layout.grid_view_layout, null));
    }

    @Override
    public void onBindViewHolder(GridAdapter.ViewHolder holder, int position) {
        holder.bindData(getItem(position), position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgView;
        private final TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img_view);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        public void bindData(GridRepModel gridRepModel, int position) {
            imgView.setImageDrawable(gridRepModel.getImg());
            tvName.setText(gridRepModel.getName());
            imgView.setOnClickListener(view -> mClickListener.onItemClick(getItem(getAdapterPosition()),getAdapterPosition()));
        }
    }
}
