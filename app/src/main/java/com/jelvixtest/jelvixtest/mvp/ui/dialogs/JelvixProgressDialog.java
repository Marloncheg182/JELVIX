package com.jelvixtest.jelvixtest.mvp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.jelvixtest.jelvixtest.R;

/**
 * Created by Oleg Romanenchuk on 27.09.2017.
 */

public class JelvixProgressDialog extends Dialog {
    private Context mContext;

    public JelvixProgressDialog(Context mContext, int themeResId) {
        super(mContext, themeResId);
        this.mContext = mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jelvix_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
