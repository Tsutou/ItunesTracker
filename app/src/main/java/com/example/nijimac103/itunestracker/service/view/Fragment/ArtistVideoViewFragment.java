package com.example.nijimac103.itunestracker.service.view.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.nijimac103.itunestracker.R;
import com.example.nijimac103.itunestracker.service.util.FragmentUtils;

public class ArtistVideoViewFragment extends Fragment {

    public static final String TAG = "ArtistVideoViewFragment";

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_video_view, container, false);

        String url = FragmentUtils.getArgsOfPreFragment(this,"URL");

        VideoView videoView = v.findViewById(R.id.videoView);
        // インターネット上のファイルを再生
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
        videoView.setMediaController(new MediaController(v.getContext()));

        return v;
    }
}
