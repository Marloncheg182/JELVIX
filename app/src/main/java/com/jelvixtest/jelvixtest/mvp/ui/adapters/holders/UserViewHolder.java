package com.jelvixtest.jelvixtest.mvp.ui.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jelvixtest.jelvixtest.R;

public class UserViewHolder extends RecyclerView.ViewHolder{

    public LinearLayout mainLayout;
    public TextView fullNameTextView;
    public ImageView avatarImageView;
    public ProgressBar avatarPreloader;

    public UserViewHolder(View itemView) {
        super(itemView);
        mainLayout = (LinearLayout) itemView.findViewById(R.id.user_layout);
        fullNameTextView = (TextView) itemView.findViewById(R.id.user_name_tv);
        avatarImageView = (ImageView) itemView.findViewById(R.id.user_avatar_iv);
        avatarPreloader = (ProgressBar)itemView.findViewById(R.id.avatar_progress);
    }
}
