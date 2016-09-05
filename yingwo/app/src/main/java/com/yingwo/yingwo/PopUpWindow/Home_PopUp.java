package com.yingwo.yingwo.PopUpWindow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yingwo.yingwo.R;

import static com.yingwo.yingwo.R.id.action_btn;
import static com.yingwo.yingwo.R.id.copy_btn;
import static com.yingwo.yingwo.R.id.tv_new;

/**
 * Created by wangyu on 8/27/16.
 */

public class Home_PopUp extends PopupWindow {
    private View mMenuView;
    protected TextView all_btn, new_btn, attention_btn , friend_btn;

    public Home_PopUp(final Activity mcontext, View.OnClickListener itemsOnClick) {
        mMenuView = LayoutInflater.from(mcontext).inflate(R.layout.home_popupwindow, null);
        all_btn = (TextView) mMenuView.findViewById(R.id.tv_all);
        new_btn = (TextView) mMenuView.findViewById(R.id.tv_new);
        attention_btn = (TextView) mMenuView.findViewById(R.id.tv_attention);
        friend_btn = (TextView) mMenuView.findViewById(R.id.tv_friendthing);
        all_btn.setOnClickListener(itemsOnClick);
        new_btn.setOnClickListener(itemsOnClick);
        attention_btn.setOnClickListener(itemsOnClick);
        friend_btn.setOnClickListener(itemsOnClick);
        this.setOutsideTouchable(true);
        this.setContentView(mMenuView);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.homepopwindow_anim_style);
    }

}
