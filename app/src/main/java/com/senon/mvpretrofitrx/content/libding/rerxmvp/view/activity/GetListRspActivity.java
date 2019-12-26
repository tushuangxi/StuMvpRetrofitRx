package com.senon.mvpretrofitrx.content.libding.rerxmvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.senon.mvpretrofitrx.R;
import com.senon.mvpretrofitrx.content.libding.entity.GetListRsp;
import com.senon.mvpretrofitrx.content.libding.http.ServiceMapParams;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseActivity;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseResponse;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.senon.mvpretrofitrx.content.libding.entity.Banner;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.view.fragment.LoginFragment;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.presenter.GetListRspPresenter;
import com.senon.mvpretrofitrx.content.libding.entity.Login;
import com.senon.mvpretrofitrx.content.libding.utils.ToastUtil;

import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

public class GetListRspActivity extends BaseActivity<interfaceUtilsAll.IBaseView, interfaceUtilsAll.Presenter> implements interfaceUtilsAll.IBaseView {

    @BindView(R.id.main_msg_tv)
    TextView main_msg_tv;
    @BindView(R.id.main_check_btn)
    Button main_check_btn;
    @BindView(R.id.main_check2_btn)
    Button main_check2_btn;
    @BindView(R.id.frame_lay)
    FrameLayout frame_lay;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    public interfaceUtilsAll.Presenter createPresenter() {
        return new GetListRspPresenter(this);
    }
    @Override
    public interfaceUtilsAll.IBaseView createView() {
        return this;
    }

    @Override
    public void init() {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_lay, new LoginFragment()).
                commitAllowingStateLoss();

    }

    @Override
    public void getListRspResult(GetListRsp data) {
        main_msg_tv.setText(data.toString());
    }

    @Override
    public void logoutResult(BaseResponse<List<Login>> data) {
        ////todo 第二个请求结果。。。
        main_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void getChaptersResult(BaseResponse<List<Login>> data) {
        main_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void getBannerResult(BaseResponse<List<Banner>> data) {
        main_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void setMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
//        return this.bindUntilEvent(ActivityEvent.PAUSE);//绑定到Activity的pause生命周期（在pause销毁请求）
        return this.bindToLifecycle();//绑定activity，与activity生命周期一样
    }


    @OnClick({R.id.main_msg_tv, R.id.main_check_btn,R.id.main_check2_btn,R.id.main_intent_btn,R.id.main_check3_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.main_check_btn: //查询1(有弹窗)
                main_msg_tv.setText("");

                getPresenter().requestGetListRspList(ServiceMapParams.getGetListRspMapParams(),true,true);

//                getPresenter().getChapters(true,true);
                break;
            case R.id.main_check2_btn:  //查询2(无弹窗)
                main_msg_tv.setText("");
//                HashMap<String,String> map2 = new HashMap<>();
//                map2.put("type","yuantong");
//                map2.put("postid","11111111111");
//                getPresenter().login(map2,false,true);

                getPresenter().getChapters(false,true);
                break;
            case R.id.main_check3_btn:// 合并查询3(zip)
                //合并请求结果
                main_msg_tv.setText("");
                getPresenter().getBanner(true,true);
                break;
            case R.id.main_intent_btn:////跳转到-->下个页面
                startActivity(new Intent(GetListRspActivity.this,NoPresenterActivity.class));
                break;
        }
    }
}
