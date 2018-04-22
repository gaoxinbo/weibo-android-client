package gaoxinbo.sinaweiboclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.activity.UserHomepageActivity;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Tweet;
import gaoxinbo.sinaweiboclient.Constants;
import gaoxinbo.sinaweiboclient.storage.internal.TimelineCache;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
    List<Tweet> list = new ArrayList<>();
    Context context;
    Gson gson = new GsonBuilder().setLenient().create();
    TimelineCache timelineCache;

    public TimelineAdapter(Context context, TimelineCache timelineCache) {
        this.timelineCache = timelineCache;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet, parent, false);
        ViewHolder vh = new ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Tweet tweet = list.get(position);
        final Tweet.User user = tweet.getUser();

        holder.tweet.setText(tweet.getText());
        holder.userName.setText(user.getName());
        holder.source.setText(Html.fromHtml(tweet.getSource()));
        holder.postTime.setText(tweet.getCreated_at().substring(0, 19));


        Glide.with(context)
                .load(user.getProfile_image_url())
                .into(holder.avatar);
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeiboApplication.getInstance(), UserHomepageActivity.class);
                intent.putExtra(Constants.USER, gson.toJson(user));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tweet;
        public TextView source;
        public TextView userName;
        public ImageView avatar;
        public TextView postTime;

        public ViewHolder(View v) {
            super(v);
            tweet =  v.findViewById(R.id.tweet);
            source = v.findViewById(R.id.source);
            userName = v.findViewById(R.id.user_name);
            avatar = v.findViewById(R.id.avatar);
            postTime = v.findViewById(R.id.post_time);
        }
    }

    public void setList(List<Tweet> list) {
        this.list = list;
        notifyDataSetChanged();

    }
}
