package com.secondary.alalamiastorez;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class YoutubeTest extends YouTubeBaseActivity {

    public static String API_KEY = "AIzaSyCH5ZCmJ7EaPtU6F01upxKzXoNx3caAUBg";
    private static final String TAG = "YoutubeTest";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_space);

        Button playBtn = findViewById(R.id.startVideo_btn);
        YouTubeThumbnailView YTV = findViewById(R.id.thumbnail);


        final YouTubePlayerView YPV = findViewById(R.id.ypv);
        final YouTubePlayer.OnInitializedListener videoListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Done");
                youTubePlayer.loadPlaylist("PLgCYzUzKIBE8h0Qaxx4VJKgRY57oafrK3");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: Failed");
            }
        };


        YouTubeThumbnailView.OnInitializedListener thumbnailListener = new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setPlaylist("PLgCYzUzKIBE8h0Qaxx4VJKgRY57oafrK3");
                Log.d(TAG, "onInitializationSuccess: OK we reached loader");

            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        YTV.initialize(API_KEY,thumbnailListener);

        YTV.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (final View v){
                    v.animate().alpha(0).setDuration(600).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            v.setVisibility(View.GONE);
                        }
                    });
                YPV.initialize(API_KEY, videoListener);
            }
            });
        }
    }
