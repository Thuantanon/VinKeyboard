package com.cxh.keyboarddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cxh.keyboarddemo.keyboard.AllUserKeyboard;
import com.cxh.keyboarddemo.keyboard.BaseKeyboardView;
import com.cxh.keyboarddemo.keyboard.IdcardKeyboardView;
import com.cxh.keyboarddemo.keyboard.VINUserKeyboard;

public class MainActivity extends AppCompatActivity {


    private EditText mEditTextVin;
    private EditText mEditTextProvince;
    private EditText mEditTextIdcard;


    private VINUserKeyboard mVINUserKeyboard;
    private AllUserKeyboard mAllUserKeyboard;
    private IdcardKeyboardView mIdcardKeyboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextVin = findViewById(R.id.text_vin);
        mEditTextProvince = findViewById(R.id.text_province);
        mEditTextIdcard = findViewById(R.id.text_idcard);

        mVINUserKeyboard = new VINUserKeyboard(this, mEditTextVin);
        mVINUserKeyboard.setEnableChangeState(false);
        mVINUserKeyboard.setTitle("请输入VIN");
        mVINUserKeyboard.bindEditText(mEditTextVin);
        mVINUserKeyboard.setKeyboardListener(new BaseKeyboardView.KeyboardListener() {
            @Override
            public void onShow(View view) {

            }

            @Override
            public void onHide(View view) {

            }
        });


        mAllUserKeyboard = new AllUserKeyboard(this, true);
        mAllUserKeyboard.setEnableChangeState(true);
        mAllUserKeyboard.setTitle("请输入车牌号");
        mAllUserKeyboard.bindEditText(mEditTextProvince);
        mAllUserKeyboard.setKeyboardListener(new BaseKeyboardView.KeyboardListener() {
            @Override
            public void onShow(View view) {

            }

            @Override
            public void onHide(View view) {

            }
        });

        mIdcardKeyboardView = new IdcardKeyboardView(this);
        mIdcardKeyboardView.setLongClickDeleteEnable(true);
        mIdcardKeyboardView.bindEditText(mEditTextIdcard);
    }
}
