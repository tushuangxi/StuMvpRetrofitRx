package com.senon.mvpretrofitrx.content.libding.rerxmvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.senon.mvpretrofitrx.R;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseActivity;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BasePresenter;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseResponse;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseView;
import com.senon.mvpretrofitrx.content.libding.entity.Login;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.model.GetListRspModel;
import com.senon.mvpretrofitrx.content.libding.widget.progress.ObserverResponseListener;
import com.senon.mvpretrofitrx.content.libding.utils.ExceptionHandle;
import com.senon.mvpretrofitrx.content.libding.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NoPresenterActivity extends BaseActivity {

    @BindView(R.id.check_msg_tv)
    TextView check_msg_tv;
    @BindView(R.id.check_btn)
    Button check_btn;

    private GetListRspModel model;

    @Override
    public int getLayoutId() {
        return R.layout.activity_no_presenter;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        model = new GetListRspModel();
    }


    @OnClick({R.id.check_msg_tv, R.id.check_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_msg_tv:
                break;
            case R.id.check_btn:
                check_msg_tv.setText("");
//                HashMap<String, String> map = new HashMap<>();
//                map.put("type", "yuantong");
//                map.put("postid", "11111111111");
//                getData(map, true, false);

                getData(true, false);
                break;
        }
    }

    private void getData(boolean isDialog, boolean cancelable) {
        model.getDelayChapters(this,isDialog, cancelable,
                this.bindToLifecycle(), new ObserverResponseListener() {
                    @Override
                    public void onNext(Object o) {
                        //这一步是必须的，判断view是否已经被销毁
                        if (this != null) {
                            //设置数据。。。
                            BaseResponse<List<Login>> response = (BaseResponse<List<Login>>) o;
                            if(response.getCode() == 0){
                                //请求成功
                                setData(response);
                            }else {
                                //"请求失败,errorCode: "+response.getCode()
                            }
                        }
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        if (this != null) {
                            ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                        }
                    }
                });
    }

    private void setData(BaseResponse<List<Login>> data) {
        check_msg_tv.setText(data.getData().toString());
    }
}
