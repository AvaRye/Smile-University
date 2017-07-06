package com.twtstudio.tjwhm.lostfound.release;

import android.content.Intent;

import com.twt.wepeiyang.commons.network.RetrofitProvider;
import com.twt.wepeiyang.commons.network.RxErrorHandler;
import com.twtstudio.tjwhm.lostfound.base.BaseBean;
import com.twtstudio.tjwhm.lostfound.base.BasePrsenterImpl;
import com.twtstudio.tjwhm.lostfound.search.SearchActivity;
import com.twtstudio.tjwhm.lostfound.waterfall.WaterfallActivity;
import com.twtstudio.tjwhm.lostfound.waterfall.WaterfallApi;
import com.twtstudio.tjwhm.lostfound.waterfall.WaterfallCallBack;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by tjwhm on 2017/7/6.
 **/

public class ReleasePresenterImpl extends BasePrsenterImpl implements ReleaseContract.ReleasePresenter {

    ReleaseContract.ReleaseView releaseView;
    ReleaseApiClient releaseApiClient = new ReleaseApiClient();
    ReleaseApi releaseApi;

    public ReleasePresenterImpl(ReleaseContract.ReleaseView releaseView) {
        this.releaseView = releaseView;
    }


    public void onSuccess(BaseBean model){
        System.out.println("ReleasePresenterImpl.onSuccess"+"abcdef");
        if(model.error_code==-1){
            releaseView.successCallBack();
        }else if(model.error_code==10001){
            releaseView.turnToAuth();
        }
    }

    @Override
    public void updateReleaseData(final Map<String, Object> map) {
        releaseApi = RetrofitProvider.getRetrofit().create(ReleaseApi.class);
        releaseApi.updateRelease(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess,new RxErrorHandler());
//        addSubscription(releaseApi.updateRelease(map),
//                new WaterfallCallBack<BaseBean>() {
//                    @Override
//                    public void onSuccess(BaseBean model) {
//                        System.out.println("ReleasePresenterImpl.onSuccess"+"abcdef");
//                        if(model.error_code==-1){
//                            releaseView.successCallBack();
//                        }else if(model.error_code==10001){
//                            releaseView.turnToAuth();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        System.out.println("ReleasePresenterImpl.onFailure"+"abcdef");
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        System.out.println("ReleasePresenterImpl.onFinish"+"abcdef");
//                    }
//                });
    }
}
