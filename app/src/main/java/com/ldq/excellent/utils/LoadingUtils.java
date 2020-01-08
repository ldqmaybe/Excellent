package com.ldq.excellent.utils;

import android.content.Context;
import android.text.TextUtils;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * author: LinDingQiang <br/>
 * created on: 2017/11/10  15:53<br/>
 * description : please enter your class description
 */
public class LoadingUtils {
    private static KProgressHUD progress;

    public static void show(Context context) {
        show(context,null);
    }

    public static void show(Context context, String msg) {
        progress = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
        if (!TextUtils.isEmpty(msg)) {
            progress.setLabel(msg);
        }
        progress.show();
    }

    public static void dismiss() {
        // progressDialog.hide();会导致android.view.WindowLeaked
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
}
