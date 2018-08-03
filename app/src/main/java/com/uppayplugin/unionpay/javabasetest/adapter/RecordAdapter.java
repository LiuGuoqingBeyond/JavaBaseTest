package com.uppayplugin.unionpay.javabasetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sinopaylib.entity.respons.RecordCountEntity;
import com.sinopaylib.entity.respons.RecordItemEntity;
import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.utils.PublicMethodUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @project：ElongGradleTaskDemo-master
 * @author：- octopus on 2017/12/7 21:36
 * @email：zhanghuan@xinguodu.com
 */
public class RecordAdapter extends BaseExpandableListAdapter {

    private List<RecordCountEntity> data;
    private Context mContext;

    public RecordAdapter(Context context) {
        this.mContext = context;
    }

    public List<RecordCountEntity> getData() {
        return data;
    }

    public void setData(List<RecordCountEntity> data) {
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return null != data ? data.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return null != data ? data.get(groupPosition).getDailyList().size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null != data ? data.get(groupPosition) : 0;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null != data ? data.get(groupPosition).getDailyList().get(childPosition) : 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    GroupViewHolder groupViewHolder = null;

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        RecordCountEntity recordCountEntity = null;
        if (null != data) {
            recordCountEntity = data.get(groupPosition);
        }
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group_title, parent, false);
            groupViewHolder.tv_day = (TextView) convertView.findViewById(R.id.tv_day);
            groupViewHolder.tv_dailyTotals = (TextView) convertView.findViewById(R.id.tv_dailyTotals);
            groupViewHolder.tv_dailyAccount = (TextView) convertView.findViewById(R.id.tv_dailyAccount);
            groupViewHolder.iv_floder = (ImageView) convertView.findViewById(R.id.iv_floder_gray);
            groupViewHolder.iv_floder.setBackgroundResource(0);

            if(isExpanded) {
                groupViewHolder.iv_floder.setBackground(mContext.getResources().getDrawable(R.drawable.icon_unfold_grey));
            } else {
                groupViewHolder.iv_floder.setBackground(mContext.getResources().getDrawable(R.drawable.icon_fold_grey));
            }

            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (null != recordCountEntity) {
            groupViewHolder.tv_day.setText(recordCountEntity.getDay());
            groupViewHolder.tv_dailyTotals.setText(recordCountEntity.getDailyTotals());
            try {
                groupViewHolder.tv_dailyAccount.setText(new BigDecimal(recordCountEntity.getDailyAccount()).divide(new BigDecimal(100)).setScale(2).toString() + " " + recordCountEntity.getCurr());
            } catch (Exception e) {
                Logger.e(e.getMessage());
                groupViewHolder.tv_dailyAccount.setText(recordCountEntity.getDailyAccount());
            }
        }
        return convertView;
    }

    ChildViewHolder childViewHolder = null;

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        RecordItemEntity recordItemEntity = null;
        if (null != data) {
            recordItemEntity = data.get(groupPosition).getDailyList().get(childPosition);
        }
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group_child, parent, false);
            childViewHolder.tv_recMethod = (TextView) convertView.findViewById(R.id.tv_recMethod);
            childViewHolder.tv_transTime = (TextView) convertView.findViewById(R.id.tv_transTime);
            childViewHolder.tv_txnAmt = (TextView) convertView.findViewById(R.id.tv_txnAmt);
            childViewHolder.tv_settAmt = (TextView) convertView.findViewById(R.id.tv_settAmt);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        if (null != recordItemEntity) {
            try {
                childViewHolder.tv_recMethod.setText(recordItemEntity.getRecMethod());

                String time = recordItemEntity.getTransTime();
                childViewHolder.tv_transTime.setText(PublicMethodUtils.getFormatTime(time));

                childViewHolder.tv_txnAmt.setText(new BigDecimal(recordItemEntity.getTxnAmt()).divide(new BigDecimal(100)).setScale(2).toString());
                childViewHolder.tv_settAmt.setText(recordItemEntity.getSettAmt().equals("-")?recordItemEntity.getSettAmt():new BigDecimal(recordItemEntity.getSettAmt()).divide(new BigDecimal(100)).setScale(2).toString());
            } catch (Exception e) {
                Logger.e(e.getMessage());
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        TextView tv_day;  // 交易日期
        TextView tv_dailyTotals;  // 交易笔数
        TextView tv_dailyAccount; // 交易金额
        ImageView iv_floder;  // 折叠和展开
    }

    class ChildViewHolder {
        TextView tv_recMethod;  // 收款方式
        TextView tv_transTime;  // 交易时间
        TextView tv_txnAmt;   // 产品金额
        TextView tv_settAmt;  // 结算金额
    }

}

