package gaoxinbo.sinaweiboclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tweet {
    String text;
    String source;
    User user;
    String created_at;

    @Setter
    @Getter
    public class User {
        String name;
        String profile_image_url;
    }
}
