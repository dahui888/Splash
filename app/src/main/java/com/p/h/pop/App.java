package com.p.h.pop;

import android.app.Application;
import com.lxj.xpopup.XPopup;
import com.p.h.R;

public class App extends Application {

    private static App app;
    public App() {
        App.app = this;
    }

    public static App instance() {
        if (app == null) {
            synchronized (App.class){
                if (app == null) {
                    app = new App();
                }
            }
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.app = this;


        //设置主色调
        XPopup.setPrimaryColor(getResources().getColor(R.color.colorAccent));
        //设置全局的动画时长
        XPopup.setAnimationDuration(200); // 传入的时长最小为0，动画的时长会影响除Drawer弹窗外的所有弹窗
        //设置弹窗的半透明背景色值
        XPopup.setShadowBgColor(10);

    }
}
