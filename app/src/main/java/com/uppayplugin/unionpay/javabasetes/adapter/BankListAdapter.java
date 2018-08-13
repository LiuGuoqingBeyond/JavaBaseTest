package com.uppayplugin.unionpay.javabasetes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testdemolib.entity.respons.BankCardInfo;
import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.javabasetes.R;
import com.uppayplugin.unionpay.javabasetes.utils.PreferencesUtil;
import com.uppayplugin.unionpay.libcommon.data.HideSensitiveUtils;
import com.uppayplugin.unionpay.libcommon.des.DESCoder;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2017/10/13
 * Time: 20:50
 */

public class BankListAdapter extends BaseRecyclerAdapter<BankCardInfo, BankListAdapter.ViewHolder> {

    public String key;

    onItemLongClickListener<BankCardInfo> mLongClickListener;

    public void setLongClickListener(onItemLongClickListener<BankCardInfo> longClickListener) {
        mLongClickListener = longClickListener;
    }

    public BankListAdapter(String key) {
        this.key = key;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.view_list_sinocard, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(getItem(position), key);
    }

    public interface onItemLongClickListener<T> {
        void onItemLongClick(T t, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;
        private PreferencesUtil prefs;
        private String carNumber;
        // 是否显示密码明文
        private boolean eyeOpen = false;
        private String cardNum;
        private final ImageView mImageView;
        private final TextView bankName;
        private final TextView bankType;
        private final TextView text_sinocard_number;
        private final ImageView imageSinoCard1;
        private final ImageView imageSinoCard2;
        private final ImageView imageSinoCard3;
        private final ImageView imageSinoCard4;
        private final ImageView imageSinoCard5;
        private final ImageView imgPassword;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mImageView = itemView.findViewById(R.id.image_sino_card);
            bankName = itemView.findViewById(R.id.text_sinocard_keeper);
            bankType = itemView.findViewById(R.id.card_type);
            text_sinocard_number = itemView.findViewById(R.id.text_sinocard_number);
            imageSinoCard1 = itemView.findViewById(R.id.image_sino_card1);
            imageSinoCard2 = itemView.findViewById(R.id.image_sino_card2);
            imageSinoCard3 = itemView.findViewById(R.id.image_sino_card3);
            imageSinoCard4 = itemView.findViewById(R.id.image_sino_card4);
            imageSinoCard5 = itemView.findViewById(R.id.image_sino_card5);
            imgPassword = itemView.findViewById(R.id.img_password);
            prefs = new PreferencesUtil(mContext);
            itemView.setOnLongClickListener(v -> {
                if (mLongClickListener != null) {
                    mLongClickListener.onItemLongClick(getItem(getAdapterPosition()), getAdapterPosition());
                }
                return false;
            });
            imgPassword.setOnClickListener(view -> {
                if (!TextUtils.isEmpty(carNumber)) {
                    togglePwdText();
                }
            });
        }

        public void bindData(BankCardInfo cardInfo, String key) {
            try {
                carNumber = DESCoder.decryptMode(cardInfo.getCardNum().replaceAll("\n", ""), key).replaceAll("\n", "");
                Logger.e(carNumber);
                cardNum = replaceAction(carNumber, "(?<=\\d{0})\\d(?=\\d{4})");
                text_sinocard_number.setText(HideSensitiveUtils.hindCardFourNumber(cardNum.replaceAll("\\*", "")));
            } catch (Exception e) {
                Logger.e(e.getMessage());
            }
            bankName.setText(cardInfo.getBankName());
            /*if (MApplication.isEnglishSystem()) {
                //英文
                bankName.setText(cardInfo.getBankNameLog());//名称
            } else {
                //中文
                bankName.setText(cardInfo.getBankName());//名称
            }*/
            /*Glide.with(mContext)
                    .load(ImgWithName.setImgResource(cardInfo.getBankName()))
                    .apply(new RequestOptions().transform(new CircleTransform(mContext)))
                    .into(mImageView);*/

            if (cardInfo.getCardType().equals("1")) {
                bankType.setText(R.string.cashier_debit_card);
            } else if (cardInfo.getCardType().equals("2")) {
                bankType.setText(R.string.cashier_credit_card);
            }
            /*OpenCountry openCountry = cardInfo.getOpenCountry();
            String up = openCountry.UP;
            String sg = openCountry.SG;
            String my = openCountry.MY;
            String hk = openCountry.HK;

            if (up.equals("0")) {
                imageSinoCard1.setVisibility(View.VISIBLE);
            }
            if (sg.equals("0")) {
                imageSinoCard2.setVisibility(View.VISIBLE);
            }
            if (my.equals("0")) {
                imageSinoCard3.setVisibility(View.VISIBLE);
            }
            if (hk.equals("0")) {
                imageSinoCard4.setVisibility(View.VISIBLE);
                imageSinoCard5.setVisibility(View.VISIBLE);
            }*/
        }

        private void togglePwdText() {
            if (eyeOpen) {
                Logger.w("show formationPassword");
                imgPassword.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_eyes_normal));
                text_sinocard_number.setText(HideSensitiveUtils.hindCardFourNumber(cardNum.replaceAll("\\*", "")));
                eyeOpen = false;
            } else {
                Logger.w("hide formationPassword");
                imgPassword.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_eyes_highlight));
                text_sinocard_number.setText(carNumber);
                eyeOpen = true;
            }
        }
    }

    private String replaceAction(String cardNum, String s) {
        return cardNum.replaceAll(s, "*");
    }
}
