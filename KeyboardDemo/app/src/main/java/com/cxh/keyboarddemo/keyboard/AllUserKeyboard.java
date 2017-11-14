package com.cxh.keyboarddemo.keyboard;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.cxh.keyboarddemo.R;


/**
 * project_name: AppWidgetTest
 * author: cxh
 * create_date: 2017/4/25 0025 16:23
 */

public class AllUserKeyboard extends BaseKeyboardView implements View.OnClickListener{

    // 数字字母键盘
    private View numberView;
    // 省份键盘
    private View provinceView;
    // 切换按钮
    private View ABCButton;
    private View ProvinceButton;

    private TextView titleText;

    // 是否可以切换输入状态
    private boolean enableChangeState = true;
    //

    private View.OnClickListener onChangeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(enableChangeState) {
                shiftInputType();
            }
        }
    };

    /**
     * @author cxh
     *
     * @time 2017/4/25 0025 下午 5:02
     * @description inputMode为默认的输入方式，true输入字母
     * @modify
     */
    public AllUserKeyboard(Activity activity, boolean isInputABC) {
        super(activity);
        // 初始化所有控件
        numberView = convertView.findViewById(R.id.number);
        provinceView = convertView.findViewById(R.id.province);
        // 回退的按钮
        convertView.findViewById(R.id.back).setOnClickListener(onDeleteClick);
        convertView.findViewById(R.id.back).setOnLongClickListener(onLongClickDelete);
        convertView.findViewById(R.id.back2).setOnClickListener(onDeleteClick);
        convertView.findViewById(R.id.back2).setOnLongClickListener(onLongClickDelete);
        // 切换的按钮
        ProvinceButton = convertView.findViewById(R.id.keyboard_shift);
        ProvinceButton.setOnClickListener(onChangeClick);
        ABCButton = convertView.findViewById(R.id._ABC);
        ABCButton.setOnClickListener(onChangeClick);
        // 标题
        titleText = (TextView) convertView.findViewById(R.id.keyboard_title);

        if(isInputABC){
            numberView.setVisibility(View.VISIBLE);
            provinceView.setVisibility(View.GONE);
        }else{
            numberView.setVisibility(View.GONE);
            provinceView.setVisibility(View.VISIBLE);
        }
    }



    @NonNull
    @Override
    public SparseArray<String> initKeyMap() {
        SparseArray<String> arrayMap = new SparseArray<String>();
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
        arrayMap.put(R.id._I, "I");
        arrayMap.put(R.id._J, "J");
        arrayMap.put(R.id._K, "K");
        arrayMap.put(R.id._L, "L");
        arrayMap.put(R.id._M, "M");
        arrayMap.put(R.id._N, "N");
        arrayMap.put(R.id._O, "O");
        arrayMap.put(R.id._P, "P");
        arrayMap.put(R.id._Q, "Q");
        arrayMap.put(R.id._R, "R");
        arrayMap.put(R.id._S, "S");
        arrayMap.put(R.id._T, "T");
        arrayMap.put(R.id._U, "U");
        arrayMap.put(R.id._V, "V");
        arrayMap.put(R.id._W, "W");
        arrayMap.put(R.id._X, "X");
        arrayMap.put(R.id._Y, "Y");
        arrayMap.put(R.id._Z, "Z");

        /**
         *  省份键盘的数据
         */
        arrayMap.put(R.id._beijing, "京");
        arrayMap.put(R.id._shanghai, "沪");
        arrayMap.put(R.id._tianjing, "津");
        arrayMap.put(R.id._chongqing, "渝");
        arrayMap.put(R.id._hebei, "冀");
        arrayMap.put(R.id._shanxi, "晋");
        arrayMap.put(R.id._liaoning, "辽");
        arrayMap.put(R.id._jilin, "吉");
        arrayMap.put(R.id._heilongjiang, "黑");
        arrayMap.put(R.id._jiangsu, "苏");
        arrayMap.put(R.id._zhejiang, "浙");
        arrayMap.put(R.id._anhui, "皖");
        arrayMap.put(R.id._fujian, "闽");
        arrayMap.put(R.id._jiangxi, "赣");
        arrayMap.put(R.id._shandong, "鲁");
        arrayMap.put(R.id._henan, "豫");
        arrayMap.put(R.id._hubei, "鄂");
        arrayMap.put(R.id._hunan, "湘");
        arrayMap.put(R.id._guangdong, "粤");
        arrayMap.put(R.id._hainan, "琼");
        arrayMap.put(R.id._sichuan, "川");
        arrayMap.put(R.id._guizhou, "贵");
        arrayMap.put(R.id._yunnan, "云");
        arrayMap.put(R.id._shanxi2, "陕");
        arrayMap.put(R.id._gansu, "甘");
        arrayMap.put(R.id._qinghai, "青");
        arrayMap.put(R.id._menggu, "蒙");
        arrayMap.put(R.id._guangxi, "桂");
        arrayMap.put(R.id._ningxia, "宁");
        arrayMap.put(R.id._xinjiang, "新");
        arrayMap.put(R.id._xizang, "藏");
        arrayMap.put(R.id._taiwan, "台");
        arrayMap.put(R.id._xianggang, "港");
        arrayMap.put(R.id._aomen, "澳");

        return arrayMap;
    }

    @Override
    public int getKeyboardLayout() {
        return R.layout.keyboard_main_all_layout;
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
        for(int i=0; i < keyMap.size(); i++){
            int layoutId = keyMap.keyAt(i);
            if(0 != layoutId){
                convertView.findViewById(layoutId).setOnClickListener(this);
            }
        }
    }

    /**
     * @author cxh
     *
     * @time 2017/4/25 0025 下午 4:48
     * @description 是否允许切换输入方式
     * @modify
     */
    public AllUserKeyboard setEnableChangeState(boolean b){
        this.enableChangeState = b;
        if(! enableChangeState){
            // 透明  不可点击
            ABCButton.setBackgroundColor(0x0000);
            ABCButton.setClickable(false);
            ProvinceButton.setBackgroundColor(0x0000);
            ProvinceButton.setClickable(false);
        }else{
            ABCButton.setBackgroundResource(R.drawable.keybooard_gird_item_selector);
            ABCButton.setClickable(true);
            ProvinceButton.setBackgroundResource(R.drawable.keybooard_gird_item_selector);
            ProvinceButton.setClickable(true);
        }
        return this;
    }

    /**
     * @author cxh
     *
     * @time 2017/4/25 0025 下午 4:48
     * @description 设置标题
     * @modify
     */
    public AllUserKeyboard setTitle(String title){
        this.titleText.setText(title);
        return this;
    }

    /**
     * @author cxh
     *
     * @time 2017/4/25 0025 下午 4:48
     * @description 设置输入方式
     * @modify
     */
    public void shiftInputType(){
        if(View.GONE == numberView.getVisibility()){
            numberView.setVisibility(View.VISIBLE);
        }else{
            numberView.setVisibility(View.GONE);
        }

        if(View.GONE == provinceView.getVisibility()){
            provinceView.setVisibility(View.VISIBLE);
        }else{
            provinceView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        String keyVal = keyMap.get(v.getId());
        if(!TextUtils.isEmpty(keyVal) && null != mEditText){
            Editable editable = mEditText.getText();
            editable.insert(mEditText.getSelectionStart(), keyVal);

            if(null != mIKeyPressedListener){
                mIKeyPressedListener.onKeyPressed(keyVal);
            }
        }
    }
}
