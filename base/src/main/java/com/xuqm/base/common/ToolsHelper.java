package com.xuqm.base.common;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class ToolsHelper {
    /**
     * 弹出提示信息  感觉比Toast好看点  不过Toast不需要依赖view
     *
     * @param view    绑定一个view才能展示
     * @param content 需要展示的内容
     */
    public static void snack(View view, CharSequence content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * EditText绑定TextInputLayout，处理一下
     *
     * @param editText        editText
     * @param textInputLayout textInputLayout
     */
    public static void addTextChangedListener(EditText editText, TextInputLayout textInputLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(textInputLayout.getError())) {//输入的时候不提示错误信息
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("");
                    textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    /**
     * 使用 TextInputLayout  提示错误信息
     *
     * @param textInputLayout TextInputLayout
     * @param msg             错判的内容
     */
    public static void showError(TextInputLayout textInputLayout, String msg) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(msg);
    }

}
