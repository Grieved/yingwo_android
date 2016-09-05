package com.yingwo.yingwo.View;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yingwo.yingwo.R;

/**
 * Created by wangyu on 8/27/16.
 */

public class Grade_PopUp extends PopupWindow {
    private View mMenuView;
    private ListView mListView;
    private String[] data;
    private ArrayAdapter<String> arrayAdapter;

    public Grade_PopUp(final Activity mcontext, AdapterView.OnItemClickListener itemClickListener) {
        data = mcontext.getResources().getStringArray(R.array.grade);
        mMenuView = LayoutInflater.from(mcontext).inflate(R.layout.grade_popup, null);
        mListView = (ListView) mMenuView.findViewById(R.id.grade_list);
        arrayAdapter = new ArrayAdapter<String>(mcontext, R.layout.list_grade_item, R.id.grade_item,data);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(itemClickListener);
        this.setOutsideTouchable(true);
        this.setContentView(mMenuView);
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

}
