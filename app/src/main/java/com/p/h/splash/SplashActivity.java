package com.p.h.splash;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.p.h.R;


/**
 * App启动动画
 * Created by 潘辉 on 2020/11/11.
 */

public class SplashActivity extends AppCompatActivity {

    private ImageView img;
    private ImageView ImgMark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        initStatus();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);
        img= (ImageView) findViewById(R.id.img_id);
        ImgMark= (ImageView) findViewById(R.id.icon_mark);

        ImgMark.post(new Runnable() {
            @Override
            public void run() {

                //加载启动背景图
                Glide.with(SplashActivity.this)
                        .load(R.drawable.splash)
                        .transition(GenericTransitionOptions.with(R.anim.spash))
                        .into(img);

                startAnimat();
            }
        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    //沉浸式状态栏
    private void initStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decoderView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decoderView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //or ?
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    //启动动画
    private void startAnimat(){

        int imgHeight=ImgMark.getHeight()/5;
        int height=getWindowManager().getDefaultDisplay().getHeight();
        int curY=height/2 + imgHeight/2;
        int dy=(height-imgHeight)/2;
        AnimatorSet set=new AnimatorSet();
        ObjectAnimator animatorTranslate=ObjectAnimator.ofFloat(ImgMark,"translationY",0,dy);
        ObjectAnimator animatorScaleX=ObjectAnimator.ofFloat(ImgMark,"ScaleX",1f,0.2f);
        ObjectAnimator animatorScaleY=ObjectAnimator.ofFloat(ImgMark,"ScaleY",1f,0.2f);
        ObjectAnimator animatorAlpha=ObjectAnimator.ofFloat(ImgMark,"alpha",1f,0.5f);
        set.play(animatorTranslate).with(animatorScaleX).with(animatorScaleY).with(animatorAlpha);
        set.setDuration(1200);
        set.setInterpolator(new AccelerateInterpolator());
        set.start();
        //给动画添加监听
        set.addListener(new Animator.AnimatorListener() {
            /**
             * 动画启动
             */
            @Override
            public void onAnimationStart(Animator animation) {

            }

            /**
             * 动画结束
             */
            @Override
            public void onAnimationEnd(Animator animation) {

                //延迟三秒跳转首页
//                ImgMark.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                        SplashActivity.this.finish();
//                    }
//                },3000);

                //直接跳转
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();

            }
            /**
             * 动画取消
             */
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            /**
             * 动画重启
             */
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


}
