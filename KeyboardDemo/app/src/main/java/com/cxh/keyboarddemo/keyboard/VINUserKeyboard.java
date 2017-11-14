package com.cxh.keyboarddemo.keyboard;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.cxh.keyboarddemo.R;
import com.cxh.keyboarddemo.util.ClipboardUtil;
import com.cxh.keyboarddemo.util.Configuration;
import com.cxh.keyboarddemo.util.ValidationUtil;

import org.xmlpull.v1.XmlPullParser;

/**
 * project_name: AppWidgetTest
 * author: cxh
 * create_date: 2017/4/25 0025 16:23
 */

public class VINUserKeyboard extends BaseKeyboardView implements View.OnClickListener {

    private final TextView hintText;
    // 复制按钮
    private TextView copyButton;
    //粘贴按钮
    private View pasteButton;

    //查询按钮
    public View queryButton;

    private TextView titleText;

    // 是否可以切换输入状态
    private boolean enableChangeState = true;

    private View.OnClickListener onCopyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (enableChangeState) {
                if (null != mEditText && !TextUtils.isEmpty(mEditText.getText().toString())) {
                    Toast.makeText(v.getContext(), "已成功复制！", Toast.LENGTH_SHORT).show();
                    ClipboardUtil.getInstance().copyText("vin", mEditText.getText().toString());
                }
            }
        }
    };
    private View.OnClickListener onPastClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (enableChangeState) {
                if (null != mEditText) {
                    mEditText.requestFocus();
                    mEditText.setFocusable(true);
                    mEditText.setFocusableInTouchMode(true);
                    String clipText = ClipboardUtil.getInstance().getClipText();
                    if (!TextUtils.isEmpty(clipText) && clipText.matches(Configuration.REGEX_VIN)) {
                        mEditText.setText(ValidationUtil.replaceBlank(clipText.toUpperCase()));
                        mEditText.setSelection(mEditText.getText().length());
                    }
                }
            }
        }
    };

    /**
     * @author cxh
     * @time 2017/4/25 0025 下午 5:02
     * @description inputMode为默认的输入方式，true输入字母
     * @modify
     */
    public VINUserKeyboard(Activity activity, EditText mEditText) {
        super(activity);
        // 初始化所有控件
        setVinText(mEditText);
        // 回退的按钮
        convertView.findViewById(R.id.back).setOnClickListener(onDeleteClick);
        convertView.findViewById(R.id.back).setOnLongClickListener(onLongClickDelete);
        // 复制按钮
        copyButton = (TextView) convertView.findViewById(R.id.keyboard_copy);
        copyButton.setOnClickListener(onCopyClick);


        // 粘贴按钮
        pasteButton = convertView.findViewById(R.id.keyboard_paste);
        pasteButton.setOnClickListener(onPastClick);

        // 查询按钮
        queryButton = convertView.findViewById(R.id.keyboard_query);

        // 标题
        titleText = (TextView) convertView.findViewById(R.id.keyboard_title);
        //下面的提示
        hintText = (TextView) convertView.findViewById(R.id.keyboard_hint);

    }




    /**
     * @author XY
     * @describe 设置vin文本监听
     */
    private void setVinText(EditText mEditText) {
        this.mEditText = mEditText;
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != hintText) {
                    if (!TextUtils.isEmpty(s.toString())) {
                        int length = s.length();
                        hintText.setText("已输入" + length + "位，还差" + (17 - length) + "位");
                        copyButton.setBackgroundResource(R.drawable.keyboard_selector);
                        try {
                            XmlPullParser xrp = copyButton.getContext().getResources().getXml(R.xml.textview_key_board_copy_selector);
                            ColorStateList csl = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                csl = ColorStateList.createFromXml(copyButton.getContext().getResources(), xrp, copyButton.getContext().getTheme());
                            } else {
                                csl = ColorStateList.createFromXml(copyButton.getContext().getResources(), xrp);
                            }
                            if (copyButton != null) {
                                copyButton.setTextColor(csl);
                                copyButton.setEnabled(true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        hintText.setText("请输入VIN码");
                        copyButton.setBackgroundResource(R.drawable.keyboard_copy_selector);
                        copyButton.setEnabled(false);
                    }
                }
            }
        });
    }


    @NonNull
    @Override
    public SparseArray<String> initKeyMap() {
        SparseArray<String> arrayMap = new SparseArray<>();
        /**
         *  数字键盘的数据
         */
        // 数字
        arrayMap.put(R.id.number_0, "0");
        arrayMap.put(R.id.number_1, "1");
        arrayMap.put(R.id.number_2, "2");
        arrayMap.put(R.id.number_3, "3");
        arrayMap.put(R.id.number_4, "4");
        arrayMap.put(R.id.number_5, "5");
        arrayMap.put(R.id.number_6, "6");
        arrayMap.put(R.id.number_7, "7");
        arrayMap.put(R.id.number_8, "8");
        arrayMap.put(R.id.number_9, "9");
        // 字母
        arrayMap.put(R.id._A, "A");
        arrayMap.put(R.id._B, "B");
        arrayMap.put(R.id._C, "C");
        arrayMap.put(R.id._D, "D");
        arrayMap.put(R.id._E, "E");
        arrayMap.put(R.id._F, "F");
        arrayMap.put(R.id._G, "G");
        arrayMap.put(R.id._H, "H");
//        arrayMap.put(R.id._I, "I");
        arrayMap.put(R.id._J, "J");
        arrayMap.put(R.id._K, "K");
        arrayMap.put(R.id._L, "L");
        arrayMap.put(R.id._M, "M");
        arrayMap.put(R.id._N, "N");
//        arrayMap.put(R.id._O, "O");
        arrayMap.put(R.id._P, "P");
//        arrayMap.put(R.id._Q, "Q");
        arrayMap.put(R.id._R, "R");
        arrayMap.put(R.id._S, "S");
        arrayMap.put(R.id._T, "T");
        arrayMap.put(R.id._U, "U");
        arrayMap.put(R.id._V, "V");
        arrayMap.put(R.id._W, "W");
        arrayMap.put(R.id._X, "X");
        arrayMap.put(R.id._Y, "Y");
        arrayMap.put(R.id._Z, "Z");


        return arrayMap;
    }

    @Override
    public int getKeyboardLayout() {
        return R.layout.keyboard_vin_layout;
    }

    @Override
    public int getBackViewId() {
        return R.id.keyboard_complete;
    }

    @Override // 由于删除键有两个，所以这里不做操作，统一交给构造函数
    public int getDeleteViewId() {
        return 0;
    }

    @Override
    public void setUpKeyboard() {

        // 设置点击事件
        // 遍历SparseArray
        for (int i = 0; i < keyMap.size(); i++) {
            int layoutId = keyMap.keyAt(i);
            if (0 != layoutId) {
                convertView.findViewById(layoutId).setOnClickListener(this);
            }
        }
    }


    /**
     * @author cxh
     * @time 2017/4/25 0025 下午 4:48
     * @description 是否允许切换输入方式
     * @modify
     */
    public VINUserKeyboard setEnableChangeState(boolean b) {
        this.enableChangeState = b;
        if (!enableChangeState) {
            // 透明  不可点击
            copyButton.setBackgroundColor(0x0000);
            copyButton.setClickable(false);
        } else {
            copyButton.setBackgroundResource(R.drawable.keybooard_gird_item_selector);
            copyButton.setClickable(true);
        }
        return this;
    }

    /**
     * @author cxh
     * @time 2017/4/25 0025 下午 4:48
     * @description 设置标题
     * @modify
     */
    public VINUserKeyboard setTitle(String title) {
        this.titleText.setText(title);
        return this;
    }

    /**
     * @author xy
     * @description 设置提示
     * @modify
     */
    public VINUserKeyboard setHint(String hint) {
        this.hintText.setText(hint);
        return this;
    }

    /**
     * @author xy
     * @description 设置提示
     * @modify
     */
    public VINUserKeyboard setHint(EditText editText) {
        this.mEditText = editText;
        return this;
    }


    @Override
    public void onClick(View v) {
        String keyVal = keyMap.get(v.getId());
        if (!TextUtils.isEmpty(keyVal) && null != mEditText) {
            Editable editable = mEditText.getText();
            editable.insert(mEditText.getSelectionStart(), keyVal);

            if (null != mIKeyPressedListener) {
                mIKeyPressedListener.onKeyPressed(keyVal);
            }
        }
    }

}
