package gaoxinbo.sinaweiboclient.service.retrofit.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tweet {
    String text;
    String source;
    User user;
    String created_at;
    Long id;
    Retweet retweeted_status;


    static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
    static final int SECOND = 1000;
    static final int MINUTE = 60 * SECOND;
    static final int HOUR = 60 * MINUTE;
    static final int DAY = 24 * HOUR;

    public String getPostTimeStr() {
        final Date now = new Date();
        try {
            final Date post_time = SIMPLE_DATE_FORMAT.parse(created_at);
            long mills = now.getTime() - post_time.getTime();
            if (mills >= DAY) {
                return String.format("%d days ago", mills / DAY);
            } else if (mills >= HOUR) {
                return String.format("%d hours ago", mills / HOUR);
            } else if (mills >= MINUTE){
                return String.format("%d mins ago", mills / MINUTE);
            } else {
                return "just now";
            }
        } catch (ParseException e) {
            return created_at;
        }
    }


    @Setter
    @Getter
    public class User {
        String name;
        String profile_image_url;
        Long id;
        Integer followers_count;
        Integer friends_count;
        Integer statuses_count;
        Integer favourites_count;
        String description;
        Boolean following;
        String province;
        String city;
    }

    @Getter
    @Setter
    public class Retweet {
        String text;
        User user;
    }
}
