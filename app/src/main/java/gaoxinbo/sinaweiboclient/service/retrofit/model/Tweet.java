package gaoxinbo.sinaweiboclient.service.retrofit.model;

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
}
