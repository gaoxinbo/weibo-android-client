package gaoxinbo.sinaweiboclient.service.retrofit.wrapper;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

import gaoxinbo.sinaweiboclient.service.retrofit.RetrofitTimelineService;
import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import retrofit2.Call;

public class UserTweetService implements TimelineService {
    RetrofitTimelineService retrofitTimelineService;

    Integer count = 50;


    public UserTweetService(RetrofitTimelineService retrofitTimelineService) {
        this.retrofitTimelineService = retrofitTimelineService;
    }

    @Override
    public Call<Timeline> getDefaultTimeline(String access_token) {
        Map<String, String> map = ImmutableMap.<String, String>builder()
                .put("access_token", access_token)
                .put("count", String.valueOf(count))
                .build();
        return retrofitTimelineService.getUserTweets(map);
    }

    @Override
    public Call<Timeline> getTimelineEarlierThan(String access_token, Long max_id) {
        Map<String, String> map = ImmutableMap.<String, String>builder()
                .put("access_token", access_token)
                .put("count", String.valueOf(count))
                .put("max_id", String.valueOf(max_id - 1))
                .build();
        return retrofitTimelineService.getUserTweets(map);
    }

    @Override
    public Call<Timeline> getTimelineLaterThan(String access_token, Long since_id) {
        Map<String, String> map = ImmutableMap.<String, String>builder()
                .put("access_token", access_token)
                .put("count", String.valueOf(count))
                .put("since_id", String.valueOf(since_id))
                .build();
        return retrofitTimelineService.getUserTweets(map);
    }
}
