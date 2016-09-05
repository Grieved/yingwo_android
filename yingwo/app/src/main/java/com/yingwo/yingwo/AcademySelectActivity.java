package com.yingwo.yingwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yingwo.yingwo.Adapter.ListAcademyGroupItemAdapter;
import com.yingwo.yingwo.model.AcademyListModel;
import com.yingwo.yingwo.model.AcademyListModel.InfoBean;
import com.yingwo.yingwo.utils.HttpControl;
import com.yingwo.yingwo.utils.UserinfoService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import static rx.schedulers.Schedulers.io;

/**
 * Created by wangyu on 9/2/16.
 */

public class AcademySelectActivity extends AppCompatActivity {
    @BindView(R.id.school_search)
    EditText schoolSearch;
    @BindView(R.id.back_btn)
    TextView backBtn;
    @BindView(R.id.school_group_list)
    ListView schoolGroupList;

    private AppCompatActivity context;
    private ListAcademyGroupItemAdapter adapter;
    private Intent SchoolIdIntent;
    private String school_id;
    private String school_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        SchoolIdIntent = getIntent();
        school_id = SchoolIdIntent.getStringExtra("school_id");
        school_name = SchoolIdIntent.getStringExtra("school_name");
        setContentView(R.layout.activity_school_select);
        ButterKnife.bind(this);
        schoolSearch.setHint("输入学院名字");
        getAcademyList();
    }

    @OnClick(R.id.back_btn)
    public void onClick() {
        finish();
    }

    public void getAcademyList() {
        Retrofit retrofit = HttpControl.getInstance().getRetrofit();
        UserinfoService service = retrofit.create(UserinfoService.class);
        service.getAcademyList(school_id)
                .subscribeOn(io())
                .unsubscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AcademyListModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AcademySelectActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AcademyListModel academyListModel) {
                        initAcademyList(academyListModel.getInfo());
                    }
                });
    }

    public void initAcademyList(List<InfoBean> data) {
        adapter = new ListAcademyGroupItemAdapter(context, data, school_name, school_id);
        schoolGroupList.setAdapter(adapter);
    }


}
