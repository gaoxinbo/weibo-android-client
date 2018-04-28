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
import android.widget.Toast;

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

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Tweet> list = new ArrayList<>();
    Context context;
    Gson gson = new GsonBuilder().setLenient().create();
    TimelineCache timelineCache;

    enum ViewType {
        Tweet,
        Fetch
    }

    public TimelineAdapter(Context context, TimelineCache timelineCache) {
        this.timelineCache = timelineCache;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (position < list.size()) {
            return ViewType.Tweet.ordinal();
        } else {
            return ViewType.Fetch.ordinal();
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ViewType.Tweet.ordinal()) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tweet, parent, false);
            TweetViewHolder vh = new TweetViewHolder(itemView);

            return vh;
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fetch_button, parent, false);
            return new FetchViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < list.size()) {
            TweetViewHolder vh = (TweetViewHolder) holder;
            final Tweet tweet = list.get(position);
            final Tweet.User user = tweet.getUser();

            vh.tweet.setText(tweet.getText());
            vh.userName.setText(user.getName());
            vh.source.setText(Html.fromHtml(tweet.getSource()));
            vh.postTime.setText(tweet.getCreated_at().substring(0, 19));


            Glide.with(context)
                    .load(user.getProfile_image_url())
                    .into(vh.avatar);
            vh.avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WeiboApplication.getInstance(), UserHomepageActivity.class);
                    intent.putExtra(Constants.USER, gson.toJson(user));
                    context.startActivity(intent);
                }
            });
        } else {
            if (list.size() > 0) {
                Toast toast = Toast.makeText(this.context, String.valueOf(list.get(list.size()-1).getId()), Toast.LENGTH_LONG );
                toast.show();
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public static class TweetViewHolder extends RecyclerView.ViewHolder {
        public TextView tweet;
        public TextView source;
        public TextView userName;
        public ImageView avatar;
        public TextView postTime;

        public TweetViewHolder(View v) {
            super(v);
            tweet =  v.findViewById(R.id.tweet);
            source = v.findViewById(R.id.source);
            userName = v.findViewById(R.id.user_name);
            avatar = v.findViewById(R.id.avatar);
            postTime = v.findViewById(R.id.post_time);
        }
    }

    public static class FetchViewHolder extends RecyclerView.ViewHolder {
        public FetchViewHolder(View v) {
            super(v);
        }
    }

    public void setList(List<Tweet> list) {
        this.list = list;
        notifyDataSetChanged();

    }
}
