package com.yingwo.yingwo;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.yingwo.yingwo.Adapter.HomePageRecycleAdapter;
import com.yingwo.yingwo.PopUpWindow.Home_PopUp;
import com.yingwo.yingwo.model.TopicModel;
import com.yingwo.yingwo.utils.HttpControl;
import com.yingwo.yingwo.utils.UserinfoService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by FJS0420 on 2016/8/5.
 */

public class HomePageActivity extends AppCompatActivity {


    @BindView(R.id.action_home)
    ImageView action_home;
    @BindView(R.id.action_find)
    ImageView action_find;
    @BindView(R.id.action_add)
    ImageView action_add;
    @BindView(R.id.action_bub)
    ImageView action_bub;
    @BindView(R.id.action_head)
    ImageView action_head;
    @BindView(R.id.swipe_page)
    SwipeRefreshLayout swipePage;

    private int GETDATA = 103;
    private Toolbar toolbar = null;
    private Home_PopUp home_popUp;
    private RecyclerView recyclerView = null;
    private HomePageRecycleAdapter recycleAdapter;
    private HttpControl httpControl;
    boolean isFirst;
    private List<TopicModel.InfoBean> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        httpControl = HttpControl.getInstance();
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getList();
        init();
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
    }

    private void init() {
        ButterKnife.bind(this);
        isFirst = true;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        action_home.setBackgroundResource(R.mipmap.home_g);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, rotationHolder);
                animator.setDuration(2000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
                home_popUp = new Home_PopUp(HomePageActivity.this, new MenuOnClickListener());
                home_popUp.showAsDropDown(toolbar, 10, 15);
            }
        });
        swipePage.post(new Runnable() {
            @Override
            public void run() {
                swipePage.setRefreshing(true);
            }
        });

        swipePage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
    }


    @OnClick(R.id.action_bub)
    public void toPost() {
//        startActivity(new Intent(this, PostActivity.class));
    }

    @OnClick(R.id.action_head)
    public void toHome() {
        startActivity(new Intent(this, PersonCenterActivity.class));
    }

    @OnClick(R.id.action_add)
    public void toRefreshthing() {
        Intent addPost = new Intent(this, RefreshThingActivity.class);
        startActivity(addPost);
    }

    public class MenuOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_all:
                    Toast.makeText(HomePageActivity.this, "toAll", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_new:
                    Toast.makeText(HomePageActivity.this, "toNew", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_attention:
                    Toast.makeText(HomePageActivity.this, "toAttention", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_friendthing:
                    Toast.makeText(HomePageActivity.this, "toFriendThing", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getList() {
        UserinfoService userinfoService = httpControl.getRetrofit().create(UserinfoService.class);
        userinfoService.getTopicList(0)
                .map(new Func1<TopicModel, List<TopicModel.InfoBean>>() {
                    @Override
                    public List<TopicModel.InfoBean> call(TopicModel topicModel) {
                        return topicModel.getInfo();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<TopicModel.InfoBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Home", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(HomePageActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<TopicModel.InfoBean> infoBeen) {
                        data = infoBeen;
                        Message message = handler.obtainMessage();
                        message.what = GETDATA;
                        message.obj = infoBeen;
                        message.sendToTarget();

//                        recycleAdapter.notifyItemRemoved(recycleAdapter.getItemCount());
//                        recycleAdapter = new HomePageRecycleAdapter(HomePageActivity.this, infoBeen);
//                        recyclerView.setAdapter(recycleAdapter);
//                        recycleAdapter.setOnItemClickListener(new HomePageRecycleAdapter.OnRecyclerViewItemClickListener() {
//                            @Override
//                            public void onItemClick(View view, TopicModel.InfoBean data) {
//                                Intent intent = new Intent(HomePageActivity.this, PostActivity.class);
//                                Bundle bundle = new Bundle();
//                                bundle.putSerializable("top", data);
//                                intent.putExtra("topBundle", bundle);
//                                startActivity(intent);
//                            }
//                        });

                    }
                });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GETDATA) {
                if (isFirst) {
                    recycleAdapter = new HomePageRecycleAdapter(HomePageActivity.this, data);
                    recyclerView.setAdapter(recycleAdapter);
                    recycleAdapter.setOnItemClickListener(new HomePageRecycleAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, TopicModel.InfoBean data) {
                            Intent intent = new Intent(HomePageActivity.this, PostActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("top", data);
                            intent.putExtra("topBundle", bundle);
                            startActivity(intent);
                        }
                    });
                    isFirst =false;
                }else{
                    recycleAdapter.setData(data);
                }
                recycleAdapter.notifyDataSetChanged();
                swipePage.setRefreshing(false);
            }
        }
    };
}
