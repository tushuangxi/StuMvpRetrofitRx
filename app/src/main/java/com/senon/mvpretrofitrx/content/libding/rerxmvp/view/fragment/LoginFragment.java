package com.senon.mvpretrofitrx.content.libding.rerxmvp.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.senon.mvpretrofitrx.R;
import com.senon.mvpretrofitrx.content.libding.entity.GetListRsp;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseFragment;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseResponse;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.senon.mvpretrofitrx.content.libding.entity.Banner;
import com.senon.mvpretrofitrx.content.libding.entity.Login;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.presenter.GetListRspPresenter;
import com.senon.mvpretrofitrx.content.libding.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * 作者：senon on 2017/12/27 16:36
 * 邮箱：a1083911695@163.com
 */

public class LoginFragment extends BaseFragment<interfaceUtilsAll.IBaseView, interfaceUtilsAll.Presenter> implements interfaceUtilsAll.IBaseView {

    @BindView(R.id.fragment_msg_tv)
    TextView fragment_msg_tv;
    @BindView(R.id.fragment_check_btn)
    Button fragment_check_btn;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_layout;
    }

    @Override
    public interfaceUtilsAll.Presenter createPresenter() {
        return new GetListRspPresenter(mContext);
    }

    @Override
    public interfaceUtilsAll.IBaseView createView() {
        return this;
    }

    @Override
    public void init() {

    }


    @Override
    public void getListRspResult(GetListRsp data) {
        fragment_msg_tv.setText(data.toString());
    }

    @Override
    public void getChaptersResult(BaseResponse<List<Login>> data) {
        fragment_msg_tv.setText(data.getData().toString());
    }

    @Override
    public void getBannerResult(BaseResponse<List<Banner>> data) {

    }

    @Override
    public void logoutResult(BaseResponse data) {

    }

    @Override
    public void setMsg(String msg) {
        ToastUtil.showShortToast(msg);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @OnClick({R.id.fragment_msg_tv, R.id.fragment_check_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_msg_tv:
                break;
            case R.id.fragment_check_btn:
                fragment_msg_tv.setText("");
//                HashMap<String,String> map = new HashMap<>();
//                map.put("type","yuantong");
//                map.put("postid","11111111111");
//                getPresenter().login(map,false,false);

                getPresenter().getChapters(false,false);
                break;
        }
    }
}
