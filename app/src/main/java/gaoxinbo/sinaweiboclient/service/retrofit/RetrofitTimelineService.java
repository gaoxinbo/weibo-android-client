package gaoxinbo.sinaweiboclient.service.retrofit;


import java.util.Map;

import gaoxinbo.sinaweiboclient.service.retrofit.model.Timeline;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitTimelineService {
    @GET("home_timeline.json")
    Call<Timeline> getTimeline(@QueryMap Map<String, String> map);
}
