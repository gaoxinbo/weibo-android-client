package gaoxinbo.sinaweiboclient.fragment.model;


import android.support.v4.app.Fragment;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Builder
@Setter
public class FragmentNode {
    @NonNull
    Fragment fragment;

    @NonNull
    String text;
}