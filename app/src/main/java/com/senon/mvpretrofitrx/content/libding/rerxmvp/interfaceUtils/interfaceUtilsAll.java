package com.senon.mvpretrofitrx.content.libding.rerxmvp.interfaceUtils;

import com.senon.mvpretrofitrx.content.libding.entity.GetListRsp;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BasePresenter;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseResponse;
import com.senon.mvpretrofitrx.content.libding.rerxmvp.base.BaseView;
import com.senon.mvpretrofitrx.content.libding.entity.Banner;
import com.senon.mvpretrofitrx.content.libding.entity.Login;

import java.util.HashMap;
import java.util.List;

import io.reactivex.ObservableTransformer;

/**
 * 作者：senon on 2017/12/27 10:30
 * 邮箱：a1083911695@163.com
 * interfaceUtilsAll  V 、P契约类
 */
public interface interfaceUtilsAll {

    interface IBaseView extends BaseView {

        void getListRspResult(GetListRsp data);

        void logoutResult(BaseResponse<List<Login>> data);

        void getChaptersResult(BaseResponse<List<Login>> data);

        void getBannerResult(BaseResponse<List<Banner>> data);

        void setMsg(String msg);

        <T> ObservableTransformer<T, T> bindLifecycle();

    }

    abstract class Presenter extends BasePresenter<IBaseView> {

        //请求1
        public abstract void requestGetListRspList(HashMap<String, String> map, boolean isDialog, boolean cancelable);

        //请求2
        public abstract void logout(HashMap<String, String> map, boolean isDialog, boolean cancelable);

        //请求3
        public abstract void getChapters(boolean isDialog, boolean cancelable);

        //请求4
        public abstract void getBanner(boolean isDialog, boolean cancelable);
    }
}
