package com.yingwo.yingwo.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.w4lle.library.NineGridlayout;
import com.yingwo.yingwo.R;
import com.yingwo.yingwo.model.TopicModel;
import com.yingwo.yingwo.utils.TImeUtiil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FJS0420 on 2016/9/2.
 */

public class HomePageRecycleAdapter extends RecyclerView.Adapter<HomePageRecycleAdapter.MyViewHolder> implements View.OnClickListener{


    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<TopicModel.InfoBean> topicModelList;
    private Context context;
    private LayoutInflater layoutInflater;

    public HomePageRecycleAdapter(Context context,List<TopicModel.InfoBean> topicModelList){
        this.topicModelList = topicModelList;
        this.context = context;
        layoutInflater=LayoutInflater. from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_refreshthing_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopicModel.InfoBean topicModel = topicModelList.get(position);
        holder.itemView.setOnClickListener(this);
        if (topicModel.getImg().contains("http")) {
            String[] urls = topicModel.getImg().split(",");
            List<String> imgurls = new ArrayList<>();
            for (String str : urls) {
                imgurls.add(str + "?imageMogr2/thumbnail/!75p");
            }
            holder.gridView.setVisibility(View.VISIBLE);
            GridviewAdapter adapter = new GridviewAdapter(context, imgurls);
            holder.gridView.setAdapter(adapter);
            holder.gridView.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(context,"第" + position + "张",Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (!topicModel.getContent().isEmpty()){
            holder.tv_content.setVisibility(View.VISIBLE);
            holder.tv_content.setText(topicModel.getContent());
        }
        String userheadurl = topicModel.getUser_face_img();
        if (userheadurl!=null) {
            Uri imgUri = Uri.parse(userheadurl);
            holder.iv_userhead.setImageURI(imgUri);
        }
//        if (imgurls.size()>5){
//            holder.gridView.setNumColumns(colnum);

//        }
        holder.tv_commentnum.setText(topicModel.getReply_cnt());
        holder.tv_likenum.setText(topicModel.getLike_cnt());
        holder.tv_releasetime.setText(TImeUtiil.getStandardDate(topicModel.getCreate_time()));
        holder.tv_userid.setText(topicModel.getUser_name());

        String title = topicModel.getTopic_title() == null ? "新鲜事" : topicModel.getTopic_title();
        holder.topic_title.setText(title);
        holder.itemView.setTag(topicModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return topicModelList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (TopicModel.InfoBean) v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_content;
        NineGridlayout gridView;
        SimpleDraweeView iv_userhead;
        TextView tv_userid;
        TextView tv_releasetime;
        TextView tv_likenum;
        TextView tv_commentnum;
        TextView topic_title;


        public MyViewHolder(View view) {
            super(view);
            tv_content=(TextView) view.findViewById(R.id. tv_content);
            gridView = (NineGridlayout) view.findViewById(R.id.gridview);
            iv_userhead = (SimpleDraweeView) view.findViewById(R.id.iv_userhead);
            tv_userid = (TextView) view.findViewById(R.id.tv_userId);
            tv_releasetime = (TextView) view.findViewById(R.id.tv_releasetime);
            tv_likenum = (TextView) view.findViewById(R.id.tv_likenum);
            tv_commentnum = (TextView) view.findViewById(R.id.tv_commentnum);
            topic_title = (TextView) view.findViewById(R.id.topic_title);
        }

    }
    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , TopicModel.InfoBean data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setData(List<TopicModel.InfoBean> data){
        topicModelList = data;
    }
}
