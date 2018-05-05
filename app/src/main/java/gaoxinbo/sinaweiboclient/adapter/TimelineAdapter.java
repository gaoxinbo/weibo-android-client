package gaoxinbo.sinaweiboclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gaoxinbo.sinaweiboclient.R;
import gaoxinbo.sinaweiboclient.activity.UserHomepageActivity;
import gaoxinbo.sinaweiboclient.adapter.helper.TweetDecorator;
import gaoxinbo.sinaweiboclient.application.WeiboApplication;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Tweet;
import gaoxinbo.sinaweiboclient.Constants;
import gaoxinbo.sinaweiboclient.service.retrofit.wrapper.RetrofitTimelineWrapper;
import gaoxinbo.sinaweiboclient.service.retrofit.wrapper.TimelineService;
import gaoxinbo.sinaweiboclient.storage.sqlite.WeiboWrapper;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Builder
public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Tweet> list;

    final Context context;
    final Gson gson;
    final TimelineService timelineService;
    final String access_token;

    enum ViewType {
        Tweet,
        Fetch
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.isEmpty()) {
                        //final retrofit2.Call<Timeline> timeline = retrofitTimelineWrapper.getDefaultTimeline(weiboWrapper.getAccessToken().get());

                    } else {
                        Long lastId = list.get(list.size() - 1).getId();
                        final Call<Timeline> timeline = timelineService.getTimelineEarlierThan(
                                access_token,
                                lastId
                        );

                        timeline.enqueue(new Callback<Timeline>() {
                            @Override
                            public void onResponse(Call<Timeline> call, Response<Timeline> response) {
                                Timeline timeline = response.body();
                                list.addAll(timeline.getStatuses());
                                Toast toast = Toast.makeText(context, String.format("fetched %d tweet(s)", timeline.getStatuses().size()), Toast.LENGTH_SHORT);
                                toast.show();
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Timeline> call, Throwable t) {

                            }
                        });

                    }

                }
            });
            return new FetchViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < list.size()) {
            TweetViewHolder vh = (TweetViewHolder) holder;
            final Tweet tweet = list.get(position);
            final Tweet.User user = tweet.getUser();

            vh.tweet.setText(TweetDecorator.getTweetSpannableStringBuilder(context,tweet));
            vh.tweet.setMovementMethod(LinkMovementMethod.getInstance());

            vh.userName.setText(user.getName());
            vh.source.setText(Html.fromHtml(tweet.getSource()));
            vh.postTime.setText(tweet.getPostTimeStr());

            if (tweet.getRetweeted_status() != null) {
                StringBuilder sb = new StringBuilder(128);
                sb.append("@");
                sb.append(tweet.getRetweeted_status().getUser().getName());
                sb.append(":");
                sb.append(tweet.getRetweeted_status().getText());
                vh.retweet.setText(sb.toString());
            } else {
                vh.retweet.setVisibility(View.GONE);
            }

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
        public TextView retweet;

        public TweetViewHolder(View v) {
            super(v);
            tweet =  v.findViewById(R.id.tweet);
            source = v.findViewById(R.id.source);
            userName = v.findViewById(R.id.user_name);
            avatar = v.findViewById(R.id.avatar);
            postTime = v.findViewById(R.id.post_time);
            retweet = v.findViewById(R.id.retweet);
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

    public Long getMaxId() {
        return list.get(0).getId();
    }


    public void insertFront(List<Tweet> elements) {
        List<Tweet> newList = new ArrayList<>(elements.size() + list.size());
        newList.addAll(elements);
        newList.addAll(list);

        list = newList;
        notifyDataSetChanged();
    }
}
