package com.p.h.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.p.h.R;
import com.p.h.pop.PopWindowActivity;

/**
 * 首页
 */
public class MainActivity extends AppCompatActivity {

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 状态栏透明
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView tv = (TextView) findViewById(R.id.tv);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        //加载小人翻跟头图片
        Glide.with(MainActivity.this)
                .load(R.drawable.pp)
                .into(iv);

        //跳转到弹框界面
        tv.setOnClickListener(v -> startActivity(new Intent(
                MainActivity.this, PopWindowActivity.class)));
    }


    //双击退出应用
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //当按下的是后退键并且是抬起的动作
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            //获取系统时间
            long secondTime = System.currentTimeMillis();
            //两次点击的间隔大于2s，则弹出Toast，并且把第二次点击的时间赋给第一次点击的时间变量，间隔小于2s则退出应用
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序!", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
