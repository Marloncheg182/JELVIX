package com.jelvixtest.jelvixtest.mvp.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jelvixtest.jelvixtest.R;
import com.jelvixtest.jelvixtest.mvp.models.feed.Data;
import com.jelvixtest.jelvixtest.mvp.models.feed.FeedResponse;
import com.jelvixtest.jelvixtest.mvp.ui.adapters.holders.UserViewHolder;

import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<Data> users = new ArrayList<>();
    private Context context;
    private UserSelectListener userSelectListener;

    public void insert(FeedResponse feedResponse) {
        users.addAll(feedResponse.getData());
        notifyDataSetChanged();
    }

    public interface UserSelectListener {
        void selectedUser(Data user);
    }

    public UsersAdapter(List<Data> users, Context context, UserSelectListener userSelectListener) {
        this.users = users;
        this.context = context;
        this.userSelectListener = userSelectListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_card, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.fullNameTextView.setText(users.get(position).getFirst_name() + " " + users.get(position).getLast_name());
        Glide.with(context).load(users.get(position).getAvatar()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.avatarPreloader.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.avatarPreloader.setVisibility(View.GONE);
                return false;
            }
        }).apply(RequestOptions.circleCropTransform()).into(holder.avatarImageView);
        holder.mainLayout.setOnClickListener(v -> userSelectListener.selectedUser(users.get(position)));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
