package com.domingoscarreiradepaola.crossover.conference.Common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.domingoscarreiradepaola.crossover.conference.Interfaces.IConfirm;
import com.domingoscarreiradepaola.crossover.conference.R;


/**
 * Created by domin on 28/08/2015.
 */
public class AlertUtil {

    public static void showOkAlert(Context context, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(context.getString(R.string.ok), null);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create().show();
    }

    public static void confirme(Context context, final IConfirm confirmation, String title, String message, String okMessage, String cancelMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton(okMessage, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmation.confirm();
            }
        });
        builder.setNegativeButton(cancelMessage,new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmation.cancel();
            }
        });
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create().show();
    }

    public static void confirme(Context context, final IConfirm confirmation, String title, String message) {

        AlertUtil.confirme(context, confirmation, title, message, context.getString(R.string.save_now), context.getString(R.string.save_after));

    }
}