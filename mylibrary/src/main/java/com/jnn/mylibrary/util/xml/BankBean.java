package com.jnn.mylibrary.util.xml;

/**
 * <pre>
 *     author : jnn
 *     e-mail : jnn_dream@126.com
 *     time   : 2018/7/31
 *     desc   : 银行限额
 * </pre>
 */
public class BankBean {
    private String bankName;
    private String bankOnceValue;
    private String bankDayValue;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankOnceValue() {
        return bankOnceValue;
    }

    public void setBankOnceValue(String bankOnceValue) {
        this.bankOnceValue = bankOnceValue;
    }

    public String getBankDayValue() {
        return bankDayValue;
    }

    public void setBankDayValue(String bankDayValue) {
        this.bankDayValue = bankDayValue;
    }
}
