package com.yingwo.yingwo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.w4lle.library.NineGridAdapter;
import com.yingwo.yingwo.utils.ImageLoadUtil;

import java.util.List;

/**
 * Created by FJS0420 on 2016/8/5.
 */

public class GridviewAdapter extends NineGridAdapter {
    Context context = null;
    private List<String> urls;
    public GridviewAdapter(Context context,List<String> urls) {
        super(context, urls);
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return (urls == null) ? 0 : urls.size();
    }

    @Override
    public String getUrl(int positopn) {
        return urls.get(positopn);
    }

    @Override
    public Object getItem(int position) {
        return (urls == null) ? null : urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int i, View convertView) {

        SimpleDraweeView iv = new SimpleDraweeView(context);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        ImageLoadUtil.showThumb(Uri.parse(urls.get(i)),iv,context);
//        iv.setImageURI(Uri.parse(urls.get(i)));
        return iv;
    }


}
