package com.cxh.keyboarddemo.keyboard;

import android.widget.EditText;

/**
 * project_name: AppWidgetTest
 * author: cxh
 * create_date: 2017/4/24 0024 11:01
 */

public interface IKeyboardOperator {

    /**
     *  关闭键盘
     */
    public void close();


    /**
     *  打开键盘
     */
    public void open();

    /**
     * 绑定输入框
     */
    public void bindEditText(EditText editText);
}
