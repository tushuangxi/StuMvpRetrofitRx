package com.senon.mvpretrofitrx.content.libding.rerxmvp.presenter;

import android.content.Context;

import com.senon.mvpretrofitrx.content.libding.entity.GetListRsp;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseResponse;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.senon.mvpretrofitrx.content.libding.entity.Banner;
import com.senon.mvpretrofitrx.content.libding.entity.Login;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.model.GetListRspModel;
import com.senon.mvpretrofitrx.content.libding.widget.progress.ObserverResponseListener;
import com.senon.mvpretrofitrx.content.libding.utils.ExceptionHandle;
import com.senon.mvpretrofitrx.content.libding.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 作者：senon on 2017/12/27 10:34
 * 邮箱：a1083911695@163.com
 */
public class GetListRspPresenter extends interfaceUtilsAll.Presenter {

    private GetListRspModel model;
    private Context context;

    public GetListRspPresenter(Context context) {
        this.model = new GetListRspModel();
        this.context = context;
    }

    @Override
    public void requestGetListRspList(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.requestGetListRspList(context, map, isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    getView().getListRspResult((GetListRsp) o);
                    getView().setMsg("请求成功");
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                //// TODO: 2017/12/28 自定义处理异常
                ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void logout(HashMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.logout(context, map, isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    getView().logoutResult((BaseResponse<List<Login>>) o);
                    getView().setMsg("请求成功");
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getChapters(boolean isDialog, boolean cancelable) {
        model.getChapters(context,isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    BaseResponse<List<Login>> response = (BaseResponse<List<Login>>) o;
                    if(response.getCode() == 0){
                        getView().getChaptersResult(response);
                        getView().setMsg("请求成功");
                    }else {
                        getView().setMsg("请求失败,errorCode: "+response.getCode());
                    }
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getBanner(boolean isDialog, boolean cancelable) {
        model.zipRequest(context,isDialog, cancelable, getView().bindLifecycle(),new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if(getView() != null){
                    getView().getBannerResult((BaseResponse<List<Banner>>) o);
                    getView().setMsg("合并请求成功");
                }
            }
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if(getView() != null){
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

}
