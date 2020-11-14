package com.p.h.pop;

import androidx.annotation.NonNull;

import android.content.Context;
import android.widget.Toast;

import com.lxj.xpopup.core.DrawerPopupView;
import com.p.h.R;

/**
 * 自定义的DrawerLayout弹窗
 */
public class CustomDrawerPopupView extends DrawerPopupView {
    public CustomDrawerPopupView(@NonNull Context context) {
        super(context);
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_drawer_popup;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.ll).setOnClickListener(view -> {
            Toast.makeText(getContext(), "nothing!!!", Toast.LENGTH_SHORT).show();
        });
    }
}
