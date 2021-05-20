package com.schanz.ktapp.ui

import android.app.AlertDialog
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class FragmentBase : Fragment() {

    private var mToast: Toast? = null

    protected fun showHelpDialog(titleId: Int, messageId: Int, confirmMessage: String?) {
        val dialog = AlertDialog.Builder(activity)
                .setTitle(titleId)
                .setMessage(messageId)
                .setNeutralButton(confirmMessage, null).create()
        dialog.show()
    }

    protected fun showHelpDialog(title: String?, message: String?, confirmMessage: String?) {
        val dialog = AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(confirmMessage, null).create()
        dialog.show()
    }

    @Suppress("SameParameterValue")
    protected fun showAchievementDialog(
            @StringRes titleId: Int,
            @StringRes messageId: Int,
            confirmMessage: String?) {
        val dialog = AlertDialog.Builder(activity)
                .setTitle(titleId)
                .setMessage(messageId)
                .setNeutralButton(confirmMessage, null).create()
        dialog.show()
    }

    protected fun showToast(message: String?) {
        if (mToast == null || mToast!!.view.windowVisibility != View.VISIBLE) {
            mToast = Toast.makeText(requireActivity().applicationContext,
                    message, Toast.LENGTH_LONG)
            mToast?.let {
                it.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 20)
                val v = it.view?.findViewById<View>(android.R.id.message) as TextView
                v.setTextColor(Color.CYAN)
                v.textSize = 18.0f
                it.show()
            }
        }
    }

    protected fun showDebugToast(message: String?) {
        mToast = Toast.makeText(requireActivity().applicationContext,
                message, Toast.LENGTH_SHORT)
        mToast?.let {
            val v = it.view?.findViewById<View>(android.R.id.message) as TextView
            v.setTextColor(Color.YELLOW)
            v.textSize = 18.0f
            it.show()
        }
    }
}
