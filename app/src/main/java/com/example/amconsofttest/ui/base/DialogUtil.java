package com.example.amconsofttest.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;

import com.example.amconsofttest.R;

public class DialogUtil {
    private static ProgressDialog sProgressDialog;

    /**
     * Shows the progress dialog
     *
     * @param activity - calling activity
     */
    public static void displayProgressDialog(final Activity activity) {
        if (sProgressDialog != null && sProgressDialog.isShowing())
            return;

        if (activity != null) {
            sProgressDialog = new ProgressDialog(activity);
            try {
                sProgressDialog.show();
                sProgressDialog.setCanceledOnTouchOutside(false);
                sProgressDialog.setCancelable(false);
                sProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                sProgressDialog.setContentView(R.layout.progress_dialog_layout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shows the progress dialog
     *
     * @param activity - calling activity
     * @param stringID - String message resource id
     * @param listener - dialog cancel callback.
     */
    public static void displayProgressDialog(final Activity activity, int stringID, DialogInterface.OnCancelListener listener) {

        if (sProgressDialog != null && sProgressDialog.isShowing())
            return;

        if (activity != null) {
            try {
                sProgressDialog = new ProgressDialog(activity);
                if (stringID != -1) {
                    sProgressDialog.setMessage(activity.getString(stringID));
                }
                sProgressDialog.setCanceledOnTouchOutside(false);
                sProgressDialog.setOnCancelListener(listener);
                sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                sProgressDialog.setCancelable(true);
                sProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void hideProgressDialog() {

        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            try {
                sProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            sProgressDialog = null;
        }
    }

}

