package com.cxh.keyboarddemo.keyboard;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.cxh.keyboarddemo.R;

import java.lang.reflect.Method;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * project_name: AppWidgetTest
 * author: cxh
 * create_date: 2017/4/24 0024 16:13
 */

public abstract class BaseKeyboardView implements IKeyboardOperator {

    // 依附的activity
    protected Activity mActivity;

    // 键盘主布局
    protected View convertView;
    // activity根布局
    protected ViewGroup rootView;
    // 输入框
    protected EditText mEditText;

    // 点击了回退
    protected OnClickListener onDeleteClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null != mEditText && mEditText.getSelectionStart() > 0) {
                Editable editable = mEditText.getText();
                editable.delete(mEditText.getSelectionStart() - 1, mEditText.getSelectionStart());
            }
        }
    };

    // 长按
    protected OnLongClickListener onLongClickDelete = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            // 光标前必须有数据
            if (isLongClickDelete && null != mEditText && mEditText.getSelectionStart() > 0) {
                Editable editable = mEditText.getText();
                editable.delete(0, mEditText.getSelectionStart());
                return true;
            }
            return false;
        }
    };

    // 点击完成
    protected OnClickListener onFinishClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null != mEditText) {
                if (null != mIKeyPressedListener) {
                    mIKeyPressedListener.onKeyPressed("close");
                }

                close();
            }
        }
    };


    // 进出动画
    protected Animation enterAnim;
    protected Animation exitAnim;

    // 键盘点击的回调
    protected IKeyPressedListener mIKeyPressedListener;

    // 默认为关闭
    protected boolean isShowing = false;
    // 设置是否长按退格删除
    protected boolean isLongClickDelete = true;

    protected SparseArray<String> keyMap;

    public BaseKeyboardView(Activity activity) {
        //
        mActivity = activity;
        rootView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        // 加载布局
        convertView = LayoutInflater.from(activity).inflate(getKeyboardLayout(), rootView, false);
        convertView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
        ));
        // 回退监听
        int backId = getBackViewId();
        if (0 != backId) {
            convertView.findViewById(backId).setOnClickListener(onFinishClick);
        }

        // 监听回删
        // 回退监听
        int deleteId = getDeleteViewId();
        if (0 != deleteId) {
            View deleteView = convertView.findViewById(deleteId);
            deleteView.setOnClickListener(onDeleteClick);

            // 长按
            deleteView.setOnLongClickListener(onLongClickDelete);
        }
        // 设置数据map
        keyMap = initKeyMap();

        // 加载动画资源
        enterAnim = AnimationUtils.loadAnimation(activity, R.anim.keyboard_push_bottom_in);
        exitAnim = AnimationUtils.loadAnimation(activity, R.anim.keyboard_push_bottom_out);
        // 默认为关闭
        isShowing = false;
        convertView.setVisibility(GONE);
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:20
     * @description 初始化键盘数据
     * @modify
     */
    public abstract
    @NonNull
    SparseArray<String> initKeyMap();


    /**
     * @author XY
     * @describe 获取键盘view对象
     */
    public View getKeyboardView() {
        return convertView;
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:21
     * @description 得到键盘的数据, 不传抛异常
     * @modify
     */
    public abstract int getKeyboardLayout();

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:27
     * @description 获取返回键的id
     * @modify
     */
    public abstract
    @IdRes
    int getBackViewId();

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:27
     * @description 获取返回键的id
     * @modify
     */
    public abstract
    @IdRes
    int getDeleteViewId();

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 5:14
     * @description 动态设置键盘数据
     * @modify
     */
    public abstract void setUpKeyboard();

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 1:53
     * @description 返回显示状态
     * @modify
     */
    public boolean isShowing() {
        return isShowing;
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 1:53
     * @description 暴露监听接口
     * @modify
     */
    public void addKeyPressedListener(IKeyPressedListener keyPressedListener) {
        this.mIKeyPressedListener = keyPressedListener;
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 2:26
     * @description 长按删除
     * @modify
     */
    public void setLongClickDeleteEnable(boolean b) {
        this.isLongClickDelete = b;
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:16
     * @description 绑定键盘s
     * @modify
     */
    @Override
    public void bindEditText(final EditText editText) {
        //
        mEditText = editText;
        // 输入框点击事件,弹出键盘
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果键盘正在显示，不做任何操作
                if (!isShowing) {
                    open();
                }
            }
        });
        //焦点监听
        editText.setFocusable(true);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                // 如果得到焦点,关闭系统输入框
                if (hasFocus) {
                    closeSystemBoard(mActivity);
                    if (!isShowing) {
                        open();
                    }
                } else {
                    if (isShowing) {
                        close();
                    }
                }
            }
        });
        // 首先屏蔽系统输入框
        // 设置不调用系统键盘
        mActivity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            // 反射设置对话框不弹出，这里可能有异常
            // editText.setShowSoftInputOnFocus(false);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (convertView.getParent() == null) {
            // 显示
            rootView.addView(convertView);
            // 设置键盘数据，所有ListView,GridView
            setUpKeyboard();
        }
    }


    /**
     * @author XY
     * @describe 定义一个监听自定义键盘显示与隐藏的接口
     */
    public interface KeyboardListener {
        //键盘显示
        void onShow(View view);

        //键盘隐藏
        void onHide(View view);
    }

    KeyboardListener keyboardListener = null;

    public void setKeyboardListener(KeyboardListener keyboardListener) {
        this.keyboardListener = keyboardListener;
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:24
     * @description 打开输入键盘
     * @modify
     */
    @Override
    public void open() {
        // 显示状态
        isShowing = true;
        // 动画
        convertView.startAnimation(enterAnim);
        convertView.setVisibility(VISIBLE);
        if (null != keyboardListener) {
            keyboardListener.onShow(convertView);
        }
    }


    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:24
     * @description 关闭输入键盘
     * @modify
     */
    @Override
    public void close() {
        isShowing = false;
        // 动画
        convertView.startAnimation(exitAnim);
        convertView.setVisibility(GONE);
        if (null != keyboardListener) {
            keyboardListener.onHide(convertView);
        }
    }

    /**
     * @author cxh
     * @time 2017/4/24 0024 下午 4:18
     * @description 关闭系统输入框
     * @modify
     */
    protected void closeSystemBoard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
        if (null != imm && imm.isActive())  //一直是true
            imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
    }
}
