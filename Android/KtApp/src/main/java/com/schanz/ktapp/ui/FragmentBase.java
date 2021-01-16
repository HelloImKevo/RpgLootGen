package com.schanz.ktapp.ui;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public abstract class FragmentBase extends Fragment {

    private Toast mToast;

    protected void showHelpDialog(int titleId, int messageId, String confirmMessage) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(titleId)
                .setMessage(messageId)
                .setNeutralButton(confirmMessage, null).create();
        dialog.show();
    }

    protected void showHelpDialog(String title, String message, String confirmMessage) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(confirmMessage, null).create();
        dialog.show();
    }

    protected void showAchievementDialog(int titleId, int messageId, String confirmMessage) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(titleId)
                .setMessage(messageId)
                .setNeutralButton(confirmMessage, null).create();
        dialog.show();
    }

    protected void showToast(String message) {
        if (mToast == null || mToast.getView().getWindowVisibility() != View.VISIBLE) {
            mToast = Toast.makeText(getActivity().getApplicationContext(),
                    message, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 20);
            TextView v = (TextView)mToast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.CYAN);
            v.setTextSize(18.0f);
            mToast.show();
        }
    }

    protected void showDebugToast(String message) {
        mToast = Toast.makeText(getActivity().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        TextView v = (TextView)mToast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.YELLOW);
        v.setTextSize(18.0f);
        mToast.show();
    }
}
