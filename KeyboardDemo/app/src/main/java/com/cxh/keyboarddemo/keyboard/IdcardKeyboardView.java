package com.cxh.keyboarddemo.keyboard;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.cxh.keyboarddemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * project_name: AppWidgetTest
 * author: cxh
 * create_date: 2017/4/24 0024 16:54
 */

public class IdcardKeyboardView extends BaseKeyboardView
        implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    // key值
    private List<String> keyVals;
    // keyboard
    private GridView mGridView;

    // 键盘的key值,身份证
    // 主要目的是给用户监听使用
    public static class KeyValue {
        public static final int KEY_HIDE = -1;
        public static final int KEY_1 = 0x0;
        public static final int KEY_2 = 0x1;
        public static final int KEY_3 = 0x2;
        public static final int KEY_4 = 0x3;
        public static final int KEY_5 = 0x4;
        public static final int KEY_6 = 0x5;
        public static final int KEY_7 = 0x6;
        public static final int KEY_8 = 0x7;
        public static final int KEY_9 = 0x8;
        public static final int KEY_X = 0x9;
        public static final int KEY_0 = 0xA;
        public static final int KEY_BACK = 0xB;
    }

    public IdcardKeyboardView(Activity activity){
        super(activity);

        // 键盘框
        mGridView = (GridView) convertView.findViewById(R.id.gv_keybord);
        mGridView.setOnItemClickListener(this);
        mGridView.setOnItemLongClickListener(this);

        // 键盘数据源
        keyVals = new ArrayList<String>();
        keyVals.add("1");
        keyVals.add("2");
        keyVals.add("3");
        keyVals.add("4");
        keyVals.add("5");
        keyVals.add("6");
        keyVals.add("7");
        keyVals.add("8");
        keyVals.add("9");
        keyVals.add("X");
        keyVals.add("0");
        keyVals.add("back");
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:56
     * @description 绑定键int值和真实值
     * @modify
     */
    @NonNull
    @Override
    public SparseArray<String> initKeyMap() {
        return new SparseArray<String>();
    }


    @Override
    public int getKeyboardLayout() {
        return R.layout.keyboard_idcard_layout;
    }

    @Override
    public int getBackViewId() {
        return R.id.layoutBack;
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 5:11
     * @description 这里的删除键是GridView的子View，所以不需要传
     * @modify
     */
    @Override
    public int getDeleteViewId() {
        return 0;
    }

    /**
     * @author cxh
     *
     * @time 2017/4/24 0024 下午 5:15
     * @description  这里需要把数组的数据传到GridView
     * @modify
     */
    @Override
    public void setUpKeyboard() {
        IdcardKeyboardAdapter adapter = new IdcardKeyboardAdapter(mActivity, keyVals);
        mGridView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position < 0 || position >= keyVals.size()) {
            throw new ArrayIndexOutOfBoundsException("position " + position + " should not exits here");
        }

        if (null != mIKeyPressedListener) {
            mIKeyPressedListener.onKeyPressed(keyVals.get(position));
        }

        // 设置editText数据
        // 如果点击退格,也就是最后一位
        if (position == keyVals.size() - 1) {
            if (mEditText.getSelectionStart() > 0) {
                Editable editable = mEditText.getText();
                editable.delete(mEditText.getSelectionStart() - 1, mEditText.getSelectionStart());
            }
        } else {
            Editable editable = mEditText.getText();
            editable.insert(mEditText.getSelectionStart(), keyVals.get(position));
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // 只处理退格键长按
        if (position == keyVals.size() - 1) {
            // 光标前必须有数据
            if (isLongClickDelete && mEditText.getSelectionStart() > 0) {
                Editable editable = mEditText.getText();
                editable.delete(0, mEditText.getSelectionStart());
                return true;
            }
        }
        return false;
    }
}
