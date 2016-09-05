package com.yingwo.yingwo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.yingwo.yingwo.Adapter.PhotoEditListAdapter;
import com.yingwo.yingwo.Listener.SoftKeyBoardListener;
import com.yingwo.yingwo.utils.HttpControl;
import com.yingwo.yingwo.utils.HttpUtil;
import com.yingwo.yingwo.utils.UserinfoService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by FJS0420 on 2016/8/1.
 */

public class RefreshThingActivity extends AppCompatActivity{

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;
    private final int MAX = 15;

    private List<PhotoInfo> mPhotoList;

//    private ChoosePhotoListAdapter mChoosePhotoListAdapter;
    private com.yingwo.yingwo.Adapter.PhotoEditListAdapter photoEditListAdapter;
    private String urls;

    //上传进度等待
    private ProgressDialog ringProgressDialog;

    @BindView(R.id.btn_choosepic)
    ImageView btn_choosepic;
    @BindView(R.id.btn_emoj)
    ImageView btn_emoj;
    @BindView(R.id.edit_content)
    EditText edit_content;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.lv_photo)
    HorizontalListView mLvPhoto;
    private Toolbar toolbar;
    private LinearLayout block_keyboardview;
    private Intent data;
    private int topic_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshthing);
        init();

    }

    @OnClick(R.id.btn_choosepic)
    public void choosepic(){

        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,15, mOnHanlderResultCallback);
    }
    private void init() {
        ButterKnife.bind(this);
        initImageLoader(this);
//        data = getIntent();
//        post_id = Integer.parseInt(data.getStringExtra("post_id"));
        topic_id = 0;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tv_title.setText("帖子回复");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        block_keyboardview = (LinearLayout) findViewById(R.id.block_keyboardview);
        mPhotoList = new ArrayList<>();
        photoEditListAdapter = new PhotoEditListAdapter(RefreshThingActivity.this, mPhotoList);
        mLvPhoto.setAdapter(photoEditListAdapter);
        //注册软键盘的监听
        SoftKeyBoardListener.setListener(this,
                new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
                    @Override
                    public void keyBoardShow(int height) {
                        block_keyboardview.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void keyBoardHide(int height) {
                        block_keyboardview.setVisibility(View.GONE);

                    }
                });

    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs();
        ImageLoader.getInstance().init(config.build());
    }

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.addAll(resultList);
                Log.i("resultonHanlderSuccess", " " + mPhotoList.size());
                if (mPhotoList.size() > MAX) {
                    for (int i = 0; i <= (mPhotoList.size() - MAX); i++)
                        mPhotoList.remove(0);
                }
                photoEditListAdapter.notifyDataSetChanged();
                Log.i("resultonHanlderSuccess", mPhotoList.size() + "");
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(RefreshThingActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_freshthing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_release) {
//            urls = "";
            ringProgressDialog = ProgressDialog.show(RefreshThingActivity.this, "连接中...", "正在发布", true);
            ringProgressDialog.setCancelable(false);
            if (mPhotoList.size() < 1) {
                String content = edit_content.getText().toString();
                if (content.isEmpty()) {
                    ringProgressDialog.dismiss();
                    Toast.makeText(this, "请添加回复内容", Toast.LENGTH_SHORT).show();
                } else {
                    realsePost(topic_id, urls, content);

                }
            } else {
                updateImg();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void updateImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token = HttpUtil.getToken("http://yw.zhibaizhi.com/yingwophp/qiniu/getAT");
                    Log.i("json", token);
                    Log.i("resultjson", mPhotoList.size() + "");
                    UploadManager uploadManager = new UploadManager();
                    for (int i = 0; i < mPhotoList.size(); i++) {
                        String data = mPhotoList.get(i).getPhotoPath();//
                        String key = null;//
                        uploadManager.put(data, key, token,
                                new UpCompletionHandler() {
                                    @Override
                                    public void complete(String key, ResponseInfo info, JSONObject res) {
                                        //res包含hash、key等信息，具体字段取决于上传策略的设置。
                                        try {
                                            Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res.get("key"));
                                            Log.i("qiniu", "\r\n " + info.isOK() + (mPhotoList.size() - 1));
                                            Message message = Message.obtain();
                                            message.what = 1;
                                            message.obj = "http://obabu2buy.bkt.clouddn.com/" + res.get("key") + ",";
                                            handler.sendMessage(message);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, null);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ringProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"上传成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        int i = 0;
        String urls = "";

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                i++;
                urls += msg.obj.toString();
            }

            if (i == (mPhotoList.size())) {
                urls = urls.substring(0, urls.length() - 1);
                String content = edit_content.getText().toString();
                realsePost(topic_id, urls, content);
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void deleteIndex(final int position, PhotoInfo photoInfo) {
        new AlertDialog.Builder(RefreshThingActivity.this).setTitle("删除提示")
                .setMessage("删除图片")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPhotoList.remove(position);
                        photoEditListAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }


    public void realsePost(final int topic_id, final String img_url, final String content) {
        Retrofit retrofit = HttpControl.getInstance().getRetrofit();
        UserinfoService userinfoService = retrofit.create(UserinfoService.class);
        userinfoService.releasePost(topic_id,content,img_url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("PostBuildingActivity", "Completed");
                        Toast.makeText(RefreshThingActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                        ringProgressDialog.dismiss();
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("PostBuildingActivity", "Error");
                        ringProgressDialog.dismiss();
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("PostActivity", s);
                    }
                });
    }
}
