package gaoxinbo.sinaweiboclient.service.retrofit.wrapper;

import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import retrofit2.Call;

public interface TimelineService {
    Call<Timeline> getDefaultTimeline(String access_token);
    Call<Timeline> getTimelineEarlierThan(String access_token, Long max_id);
    Call<Timeline> getTimelineLaterThan(String access_token, Long since_id);
}
