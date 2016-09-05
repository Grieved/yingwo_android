package com.yingwo.yingwo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.yingwo.yingwo.Adapter.PostRecyclerAdapter;
import com.yingwo.yingwo.View.AutoLoadRecyclerView;
import com.yingwo.yingwo.model.PostListEntity;
import com.yingwo.yingwo.model.TopicModel;
import com.yingwo.yingwo.utils.HttpControl;
import com.yingwo.yingwo.utils.UserinfoService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by FJS0420 on 2016/8/5.
 */

public class PostActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ib_like)
    ImageButton ibLike;
    @BindView(R.id.swipe_page)
    SwipeRefreshLayout swipePage;

    private Toolbar toolbar;
    private int POSTLIST = 10;
    private boolean isFirst;
    private AutoLoadRecyclerView mRecyclerVeiew;
    private PostRecyclerAdapter mAdapter;
    private Intent topIntent;
    private TopicModel.InfoBean topBean;
    private List<PostListEntity.InfoBean> data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        isFirst = true;
        topIntent = getIntent();
        Bundle topBundle = topIntent.getBundleExtra("topBundle");
        topBean = (TopicModel.InfoBean) topBundle.getSerializable("top");
        getPostList();
        swipePage.post(new Runnable() {
            @Override
            public void run() {
                swipePage.setRefreshing(true);
            }
        });

        swipePage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPostList();
            }
        });
        mRecyclerVeiew = (AutoLoadRecyclerView) findViewById(R.id.rv_post);
        mRecyclerVeiew.setLayoutManager(new LinearLayoutManager(this));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tv_title.setText("帖子");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void buildItem(View view) {
        Intent intent = new Intent(this, PostBuildingActivity.class);
        intent.putExtra("post_id", topBean.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPostList();
    }

    public void getPostList() {
        Retrofit retrofit = HttpControl.getInstance().getRetrofit();
        UserinfoService userinfoService = retrofit.create(UserinfoService.class);
        userinfoService.getPostList(Integer.parseInt(topBean.getId()))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PostListEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d("PostActivity", "Completed");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("PostActivity", "Error");
                    }

                    @Override
                    public void onNext(PostListEntity postListEntity) {
                        if (postListEntity.getStatus() != 1) {
                            onError(new Exception());
                        } else {
                            data = postListEntity.getInfo();
                            Message message = handler.obtainMessage();
                            message.what = POSTLIST;
                            message.sendToTarget();
                        }
                    }
                });
    }

    @OnClick(R.id.ib_like)
    public void Like() {
        getPostList();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == POSTLIST) {
                if (isFirst) {
                    mAdapter = new PostRecyclerAdapter(PostActivity.this, data, topBean);
                    mRecyclerVeiew.setAdapter(mAdapter);
                    isFirst = false;
                } else {
                    mAdapter.setData(data);
                }
                mAdapter.notifyDataSetChanged();
                swipePage.setRefreshing(false);
            }
        }
    };
}
