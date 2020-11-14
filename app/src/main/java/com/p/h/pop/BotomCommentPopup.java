package com.p.h.pop;

import androidx.annotation.NonNull;

import android.content.Context;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.p.h.R;

/**
 * 自定义底部弹框
 */
public class BotomCommentPopup extends BottomPopupView {
    public BotomCommentPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }
    // 最大高度为Window的0.85
    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext())*.85f);
    }
}
