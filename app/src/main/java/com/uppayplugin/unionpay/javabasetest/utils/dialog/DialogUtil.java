package com.uppayplugin.unionpay.javabasetest.utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.uppayplugin.unionpay.javabasetest.R;
import com.uppayplugin.unionpay.javabasetest.exception.MposException;
import com.uppayplugin.unionpay.javabasetest.log.AppLogger;

/**
 * 对话框工具类
 *
 * @author yangxin
 */
public class DialogUtil {

    private static Dialog dialog = null;

    public static int LAYOUT_ONE = R.layout.dialog_loading_style_1;
    public static int LAYOUT_TWO = R.layout.dialog_loading_style_2;

    private static Handler handler = new Handler(Looper.getMainLooper());
    /**
     * 白板
     */
    public static int STYLE_LOADING = R.style.loadingdialog;


    /**
     * 半透明
     */
    public static int STYLE_HALF_TRANSPARENT = R.style.transparentdialog;
    public static int STYLE_HALF_TRANSPARENTS = R.style.loadingdialog;

    public static Dialog createDialog(final Context ctx, final int style) {
        try {
            if (dialog != null) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            dialog = new Dialog(ctx, style);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public static void dissmiss() {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * loading对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showLoadingDialog(final Context ctx, final int text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    dialog.setCancelable(false);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                    final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                            .getBackground();
                    fireImg.post(new Runnable() {
                        @Override
                        public void run() {
                            animDrawable.start();
                        }
                    });
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * @param ctx
     * @param text
     * @param cancel :点击弹框外部是否可以消失
     * @return
     */
    public static void showLoadingDialog(final Context ctx, final int text,
                                         final boolean cancel) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setCanceledOnTouchOutside(cancel);
                    dialog.setCancelable(cancel);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                    final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                            .getBackground();
                    fireImg.post(new Runnable() {
                        @Override
                        public void run() {
                            animDrawable.start();
                        }
                    });
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * loading对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showLoadingDialog(final Context ctx, final String text) {
        try {
            handler.postAtFrontOfQueue(new Runnable() {
                @Override
                public void run() {
                    try {
                        dialog = createDialog(ctx, STYLE_LOADING);
                        dialog.setContentView(R.layout.dialog_loading_style_1);
                        dialog.setCancelable(false);
                        ImageView fireImg = dialog
                                .findViewById(R.id.progressBar1);
                        TextView tv = dialog.findViewById(R.id.tv);
                        tv.setText(text);
                        fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                        final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                                .getBackground();
                        fireImg.post(new Runnable() {
                            @Override
                            public void run() {
                                animDrawable.start();
                            }
                        });
                        dialog.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param ctx
     * @param text
     * @param cancel :点击弹框外部是否可以消失
     * @return
     */
    public static void showLoadingDialog(final Context ctx, final String text,
                                         final boolean cancel) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setCanceledOnTouchOutside(cancel);
                    dialog.setCancelable(cancel);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    final ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                    final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                            .getBackground();
                    handler.postAtFrontOfQueue(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                fireImg.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        animDrawable.start();
                                    }
                                });
                                dialog.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @param ctx
     * @param text
     * @param cancel :点击弹框外部是否可以消失
     * @return
     */
    public static void showLoadingDialog(final Context ctx, final String text,
                                         final boolean cancel, final boolean cancels) {
        handler.postAtFrontOfQueue(() -> {
            try {
                dialog = createDialog(ctx, STYLE_LOADING);
                dialog.setCanceledOnTouchOutside(cancel);
                dialog.setCancelable(cancels);
                dialog.setContentView(R.layout.dialog_loading_style_1);
                final ImageView fireImg = dialog
                        .findViewById(R.id.progressBar1);
                TextView tv = dialog.findViewById(R.id.tv);
                tv.setText(text);
                fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                        .getBackground();
                handler.postAtFrontOfQueue(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            fireImg.post(new Runnable() {
                                @Override
                                public void run() {
                                    animDrawable.start();
                                }
                            });
                            dialog.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 成功对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showSuccessDialog(final Context ctx, final int text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 成功对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showSuccessDialog(final Context ctx, final String text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 成功对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showSuccessDialog(final Context ctx, final int text,
                                           long timeUnits) {

        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * 成功对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showSuccessDialog(final Context ctx, final int text,
                                           long timeUnits, final boolean flag) {

        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    dialog.setCancelable(flag);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * 成功对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showSuccessDialog(final Context ctx,
                                           final String text, long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * loading对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showLoadingDialog(final Context ctx, final int text,
                                           long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    dialog.setCancelable(false);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                    final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                            .getBackground();
                    fireImg.post(new Runnable() {
                        @Override
                        public void run() {
                            animDrawable.start();
                        }
                    });
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * loading对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showLoadingDialog(final Context ctx,
                                           final String text, long timeUnits) {
        try {
            handler.postAtFrontOfQueue(new Runnable() {
                @Override
                public void run() {
                    try {
                        dialog = createDialog(ctx, STYLE_LOADING);
                        dialog.setContentView(R.layout.dialog_loading_style_1);
                        dialog.setCancelable(false);
                        ImageView fireImg = dialog
                                .findViewById(R.id.progressBar1);
                        TextView tv = dialog.findViewById(R.id.tv);
                        tv.setText(text);
                        fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                        final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                                .getBackground();
                        fireImg.post(new Runnable() {
                            @Override
                            public void run() {
                                animDrawable.start();
                            }
                        });
                        dialog.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showAlertDialog(final Context ctx, final int text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showAlertDialog(final Context ctx, final String text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static void showAlertDialog(final Context ctx, final int text,
                                       long timeUnits) {
        Activity activity = (Activity) ctx;
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);

                    if (!activity.isFinishing() && !activity.isDestroyed()) {
                        dialog.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        if (!activity.isFinishing() && !activity.isDestroyed()) {
                            dialog.dismiss();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
    }

    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @param cancel
     * @return
     */
    public static void showAlertDialog(final Context ctx, final String text,
                                       long timeUnits, final boolean cancel) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setCancelable(cancel);
                    dialog.setCanceledOnTouchOutside(cancel);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
    }

    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static void showAlertDialog(final Context ctx, final String text,
                                       long timeUnits) {
        handler.postAtFrontOfQueue(() -> {
            try {
                // FIXME: 2017/10/19 zhanghuan 暂时屏蔽Dialog显示，改用Toast显示
                /*dialog = createDialog(ctx, STYLE_LOADING);
                dialog.setContentView(R.layout.dialog_loading_style_1);
                ImageView fireImg = (ImageView) dialog
                        .findViewById(R.id.progressBar1);
                TextView tv = (TextView) dialog.findViewById(R.id.tv);
                tv.setText(text);
                fireImg.setBackgroundResource(R.drawable.dialog_alert);
                dialog.show();*/

                AppLogger.e("zhanghuan_", "error_Text:" + text);
                // TODO: 2017/10/23 zhanghuan 遇到返回null的情况下，暂时不提示信息，后期修改
                if (!TextUtils.isEmpty(text) && !(text.toLowerCase().contains("null"))) {
                    ToastUtils.showLong("" + text);
                } /*else {
                    toastUtil.showLongToast("交易失败");
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    /*public static void showAlertDialog(final Context ctx, final String text,
            long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = (ImageView) dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = (TextView) dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    /**
     * 警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    /*public static void showAlertDialog(final Context ctx, final String text,
                                       long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_loading_style_1);
                    ImageView fireImg = (ImageView) dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = (TextView) dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    if (((Activity) ctx).isFinishing() || ((Activity) ctx).isDestroyed()) {
                        return;
                    }
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
    }*/

    /**
     * 半透明成功对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showTransparentSuccessDialog(final Context ctx,
                                                    final int text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                    dialog.setContentView(R.layout.dialog_loading_style_2);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 半透明成功对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showTransparentSuccessDialog(final Context ctx,
                                                      final int text, long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                    dialog.setContentView(R.layout.dialog_loading_style_2);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_sucess);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * 半透明警告对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showTransparentAlertDialog(final Context ctx,
                                                  final int text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                    dialog.setContentView(R.layout.dialog_loading_style_2);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 半透明警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showTransparentAlertDialog(final Context ctx,
                                                    final int text, long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                    dialog.setContentView(R.layout.dialog_loading_style_2);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_alert);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    /**
     * 半透明警告对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showTransparentLoadingDialog(final Context ctx,
                                                    final int text) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.dialog_loading_style_2);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                    final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                            .getBackground();
                    fireImg.post(new Runnable() {
                        @Override
                        public void run() {
                            animDrawable.start();
                        }
                    });
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 半透明loading对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showTransparentLoadingDialog(final Context ctx,
                                                    final int text, final boolean cancelble) {
        handler.postAtFrontOfQueue(() -> {
            try {
                dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                dialog.setCanceledOnTouchOutside(cancelble);
                dialog.setCancelable(cancelble);
                dialog.setContentView(R.layout.dialog_loading_style_2);
                ImageView fireImg = dialog
                        .findViewById(R.id.progressBar1);
                TextView tv = dialog.findViewById(R.id.tv);
                tv.setText(text);
                fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                        .getBackground();
                fireImg.post(animDrawable::start);
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 半透明loading对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showTransparentLoadingDialogs(final Context ctx,
                                                     final int text, final boolean cancelble) {
        handler.postAtFrontOfQueue(() -> {
            try {
                dialog = createDialog(ctx, STYLE_HALF_TRANSPARENTS);
                dialog.setCanceledOnTouchOutside(cancelble);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialog_loading_style_2);
                ImageView fireImg = dialog
                        .findViewById(R.id.progressBar1);
                TextView tv = dialog.findViewById(R.id.tv);
                tv.setText(text);
                fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                        .getBackground();
                fireImg.post(animDrawable::start);
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 半透明警告对话框
     *
     * @param ctx
     * @param text
     * @param timeUnits
     * @return
     */
    public static Dialog showTransparentLoadingDialog(final Context ctx,
                                                      final int text, long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_HALF_TRANSPARENT);
                    dialog.setContentView(R.layout.dialog_loading_style_2);
                    ImageView fireImg = dialog
                            .findViewById(R.id.progressBar1);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                    final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                            .getBackground();
                    fireImg.post(new Runnable() {
                        @Override
                        public void run() {
                            animDrawable.start();
                        }
                    });
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
        return dialog;
    }

    public static String showStr(MposException ex) {
        return ex.getErrorMsg() + "(" + ex.getErrorCode() + ")";
    }

    /**
     * loading对话框
     *
     * @param ctx
     * @param text
     * @return
     */
    public static void showWebDialog(final Context ctx, final String text) {
        try {
            handler.postAtFrontOfQueue(new Runnable() {
                @Override
                public void run() {
                    try {
                        dialog = createDialog(ctx, STYLE_LOADING);
                        dialog.setContentView(R.layout.dialog_loading_style_1);
                        ImageView fireImg = dialog
                                .findViewById(R.id.progressBar1);
                        TextView tv = dialog.findViewById(R.id.tv);
                        tv.setText(text);
                        fireImg.setBackgroundResource(R.drawable.dialog_loading_anim);
                        final AnimationDrawable animDrawable = (AnimationDrawable) fireImg
                                .getBackground();
                        fireImg.post(new Runnable() {
                            @Override
                            public void run() {
                                animDrawable.start();
                            }
                        });
                        dialog.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showTipDialog(final Context ctx, final String text,
                                     long timeUnits) {
        handler.postAtFrontOfQueue(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog = createDialog(ctx, STYLE_LOADING);
                    dialog.setContentView(R.layout.dialog_tip_style);
                    dialog.setCancelable(false);
                    TextView tv = dialog.findViewById(R.id.tv);
                    tv.setText(text);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, timeUnits);
    }

}
