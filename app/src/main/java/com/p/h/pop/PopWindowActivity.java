package com.p.h.pop;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.p.h.R;

public class PopWindowActivity extends AppCompatActivity {

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;
    private Button bt6;
    private Button bt7;
    private Button bt8;
    private Button bt9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xpop);
        initView();

        /**
         * 状态栏透明
         */
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        bt5 = (Button) findViewById(R.id.bt5);
        bt6 = (Button) findViewById(R.id.bt6);
        bt7 = (Button) findViewById(R.id.bt7);
        bt8 = (Button) findViewById(R.id.bt8);
        bt9 = (Button) findViewById(R.id.bt9);

        //显示确认和取消对话框
        bt1.setOnClickListener(view -> {
            BasePopupView show = new XPopup.Builder(this)
                    .customAnimator(new RotateAnimator())//使用自定义动画
                    .asConfirm("我是标题", "我是内容",
                            new OnConfirmListener() {
                                @Override
                                public void onConfirm() {
                                    Toast.makeText(PopWindowActivity.this, "弹框", Toast.LENGTH_SHORT).show();
                                }
                            })
                    .show();
        });

        //显示带输入框的确认和取消对话框
        bt2.setOnClickListener(view -> {
            new XPopup.Builder(this).asInputConfirm("我是标题", "请输入内容。",
                    new OnInputConfirmListener() {
                        @Override
                        public void onConfirm(String text) {
                            Toast.makeText(PopWindowActivity.this, "带输入框" + text, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });

        //显示中间弹出的列表弹窗
        bt3.setOnClickListener(view -> {
            new XPopup.Builder(this)
                    //.maxWidth(600)
                    .asCenterList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {
                                    Toast.makeText(PopWindowActivity.this, "中间弹出" + text, Toast.LENGTH_SHORT).show();
                                }
                            })
                    .show();
        });

        //显示中间弹出的加载框
        bt4.setOnClickListener(view -> {
            new XPopup.Builder(this)
                    .asLoading("正在加载哦...")
                    .show();

//            /**
//             * 关弹框
//             */
//            BasePopupView popupView = new XPopup.Builder(this)
//                    .asLoading("正在加载中")
//                    .show();
//
//            //有三个消失方法可供选择：
//            popupView.dismiss();  //立即消失
//            popupView.delayDismiss(300);//延时消失，有时候消失过快体验可能不好，可以延时一下
//            popupView.smartDismiss(); //会等待弹窗的开始动画执行完毕再进行消失，可以防止接口调用过快导致的动画不完整。

        });

        //显示从底部弹出的列表弹窗
        bt5.setOnClickListener(view -> {
            // 这种弹窗从 1.0.0版本开始实现了优雅的手势交互和智能嵌套滚动
            new XPopup.Builder(this)
                    .asBottomList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                            (position, text) -> {
                                Toast.makeText(PopWindowActivity.this, "显底部弹出的列表弹窗" + text, Toast.LENGTH_SHORT).show();
                            })
                    .show();
        });

        //显示依附于某个View或者某个点的弹窗
        bt6.setOnClickListener(view -> {
            new XPopup.Builder(this)
                    .atView(bt6)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                    .asAttachList(new String[]{"分享", "编辑", "不带icon"},
                            new int[]{R.mipmap.icon, R.mipmap.icon},
                            (position, text) -> {
                                Toast.makeText(PopWindowActivity.this, "显示依附于某个View或者某个点的弹窗" + text, Toast.LENGTH_SHORT).show();
                            })
                    .show();
        });

        //大图浏览弹窗
//        bt7.setOnClickListener(view -> {
//            //当你点击图片的时候执行以下代码：
////            // 多图片场景（你有多张图片需要浏览）
////            //srcView参数表示你点击的那个ImageView，动画从它开始，结束时回到它的位置。
////            new XPopup.Builder(this).asImageViewer(imageView, position, list, new OnSrcViewUpdateListener() {
////                @Override
////                public void onSrcViewUpdate(ImageViewerPopupView popupView, int position) {
////                    // 作用是当Pager切换了图片，需要更新源View
////                    popupView.updateSrcView((ImageView) recyclerView.getChildAt(position));
////                }
////            }, new ImageLoader())
////                    .show();
//
//            // 单张图片场景
//            new XPopup.Builder(this)
//                    .asImageViewer(imageView, url, new ImageLoader())
//                    .show();
//
//            // 图片加载器，XPopup不负责加载图片，需要你实现一个图片加载器传给我，这里以Glide为例，如果图片加载不出来，往往是网络问题，或者图片大小过大，一般跟XPopup无关，请自行检查。
//            class ImageLoader implements XPopupImageLoader {
//                @Override
//                public void loadImage(int position, @NonNull String url, @NonNull ImageView imageView) {
//                    Glide.with(imageView).load(url).into(imageView);
//                }
//                //必须实现这个方法，返回uri对应的缓存文件，可参照下面的实现，内部保存图片会用到。如果你不需要保存图片这个功能，可以返回null。
//                @Override
//                public File getImageFile(@NonNull Context context, @NonNull Object uri) {
//                    try {
//                        return Glide.with(context).downloadOnly().load(uri).submit().get();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//            }
//        });


        //侧边栏
        bt8.setOnClickListener(view -> {
            new XPopup.Builder(this)
                    .popupPosition(PopupPosition.Left)//左边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(new CustomDrawerPopupView(this))
                    .show();
// 注意：如果每次show的时候都new一个弹窗对象，那么弹窗内的数据和状态则无法保存，因为都是新的；
// 如果想保存，则先new一个弹窗对象，每次都显示同一个对象即可
        });



        //自定义底部弹框
        bt9.setOnClickListener(view -> {
            new XPopup.Builder(this)
                    .asCustom(new BotomCommentPopup(this))
                    .show();
        });

    }
}

//自定义旋转动画
class RotateAnimator extends PopupAnimator {
    @Override
    public void initAnimator() {
        targetView.setScaleX(0);
        targetView.setScaleY(0);
        targetView.setAlpha(0);
        targetView.setRotation(360);
    }

    @Override
    public void animateShow() {
        targetView.animate().rotation(0).scaleX(1).scaleY(1).alpha(1).setInterpolator(new FastOutSlowInInterpolator()).setDuration(300).start();
    }

    @Override
    public void animateDismiss() {
        targetView.animate().rotation(360).scaleX(0).scaleY(0).alpha(0).setInterpolator(new FastOutSlowInInterpolator()).setDuration(300).start();
    }

//    动画使用方法
//    XPopup.get(getContext())
//            .asConfirm(...)
//        .customAnimator(new RotateAnimator())
//            .show();
}

