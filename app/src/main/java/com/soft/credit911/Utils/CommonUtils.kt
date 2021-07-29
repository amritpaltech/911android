package com.soft.credit911.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.soft.credit911.R;

import java.util.logging.Logger;

public class CommonUtils {

    private Dialog dialog_progress;
    private static final String TAG = CommonUtils.class.getSimpleName();

    /*calling  showProgress*/
    public void showProgress(Context context) {
        try {
            if (dialog_progress == null) {
                initProgress(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!dialog_progress.isShowing() && !((Activity) context).isFinishing()) {
                dialog_progress.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*calling  initProgress*/
    private void initProgress(Context context) {
        try {
            dialog_progress = new Dialog(context, R.style.CustomDialogTime);
            dialog_progress.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog_progress.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
            dialog_progress.setCancelable(false);
            dialog_progress.setContentView(R.layout.dialog_progress_overlay);
//            dialog_progress.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*calling  hideProgress*/
    public void hideProgress() {
        try {
            if (dialog_progress != null) {

                if (dialog_progress.isShowing())
                    dialog_progress.dismiss();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static void showdialog(String msg, Context context, boolean closeActivity) {
        try {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent_background)));
            dialog.setContentView(R.layout.dialog_custom);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            TextView btnCloseDialog = (TextView) dialog.findViewById(R.id.btnCloseDialog);

            TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
            tv_msg.setText(msg);

            btnCloseDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                        if (closeActivity)
                            ((Activity) context).onBackPressed();
                    }
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
