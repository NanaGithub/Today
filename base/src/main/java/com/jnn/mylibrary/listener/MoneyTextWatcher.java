package com.jnn.mylibrary.listener;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 金钱输入检测
 * Created by sgy on 2016/11/7.
 */
public class MoneyTextWatcher implements TextWatcher {

    private EditText etMoney;

    public MoneyTextWatcher(EditText etMoney) {
        this.etMoney = etMoney;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                //小数点后两位
                s = s.toString().substring(0, s.toString().indexOf(".") + 3);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
            if (s.toString().trim().substring(0).equals(".")) {
                //输入“.”后自动前面加“0”
                s = "0" + s;
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
            if (s.toString().startsWith("0") &&
                    s.toString().trim().length() > 1) {//
                if (!s.toString().substring(1, 2).equals(".")) {
                    etMoney.setText(s.subSequence(0, 1));
                    etMoney.setSelection(1);
                }
            }
            if (s.toString().substring(s.toString().indexOf(".") + 1).contains(".")) {
                //输入多个点的时候
                s = s.toString().substring(0, s.toString().indexOf(".") + 1);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
        }
        if (!TextUtils.isEmpty(s.toString()) && Double.parseDouble(s.toString()) > 1000.00) {
            etMoney.setText("1000.00");
            etMoney.setSelection(7);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().trim().substring(0).equals("00")) {
            //输入“0”后自动前面加“.”
            etMoney.setText("0.");
            etMoney.setSelection(2);
        }
    }
}
