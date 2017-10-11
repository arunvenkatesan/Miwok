package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private MediaPlayer player;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        player.pause();
                        player.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        player.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseResources();
                    }
                }
            };
    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_colors,container,false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Colors> colors = new ArrayList<Colors>();
        colors.add(new Colors("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colors.add(new Colors("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colors.add(new Colors("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Colors("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Colors("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colors.add(new Colors("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colors.add(new Colors("Dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Colors("Mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        ColorAdapter colorAdapter = new ColorAdapter(getActivity(), colors, R.color.category_colors);
        ListView listView = (ListView) rootView.findViewById(R.id.colors);

        listView.setAdapter(colorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Colors pos = colors.get(position);
                releaseResources();
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    player = MediaPlayer.create(getActivity(), pos.getmAudioId());
                    player.start();
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseResources();
                        }
                    });
                }
            }

        });
        return rootView;

    }

    public void releaseResources() {
        if (player != null) {
            player.release();
            player = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseResources();
    }


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseResources();
        }
    };
}
