package com.example.retrofitrxjavaokhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 主activity
 *
 * @author jkloshhm  2018-05-18
 */

public class MainActivity extends AppCompatActivity {

    private Observable observable;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        HttpUtils.request(
                HttpUtils.createApiService().getGetMenuFromId("25d5a30486298", "00100010070000000001"),
                new HttpUtils.IResponseListener<CookMenuByIdBean>() {
                    @Override
                    public void onSuccess(CookMenuByIdBean data) {
                        if (data != null) {
                            ToastUtils.showShortToast("加载成功---");
                            textView.setText("getMsg()==" + data.getMsg()
                                    + "getRetCode()==" + data.getRetCode()
                                    + "getResult().getCtgTitles()()==" + data.getResult().getCtgTitles());
                        }
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.showShortToast("加载失败---");
                    }
                });

        //基础方法
       /* HttpUtils.createApiService()
                .getGetMenuFromId("25d5a30486298", "00100010070000000001")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookMenuByIdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CookMenuByIdBean cookMenuByIdBean) {
                        ToastUtils.showShortToast("加载成功---");
                        if (cookMenuByIdBean != null) {
                            textView.setText("getMsg()==" + cookMenuByIdBean.getMsg()
                                    + "getRetCode()==" + cookMenuByIdBean.getRetCode()
                                    + "getResult().getCtgTitles()()==" + cookMenuByIdBean.getResult().getCtgTitles());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showShortToast("加载失败---");
                    }

                    @Override
                    public void onComplete() {
                        ToastUtils.showShortToast("加载完成---");

                    }
                });*/
    }
}
