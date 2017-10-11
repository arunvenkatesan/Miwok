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

import static com.example.android.miwok.R.id.family;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

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
    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_family,container,false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Family> fly = new ArrayList<Family>();
        fly.add(new Family("father", "әpә", R.drawable.family_father, R.raw.family_father));
        fly.add(new Family("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        fly.add(new Family("son", "angsi", R.drawable.family_son, R.raw.family_son));
        fly.add(new Family("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        fly.add(new Family("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        fly.add(new Family("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        fly.add(new Family("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        fly.add(new Family("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        fly.add(new Family("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        fly.add(new Family("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        FamilyAdapter flyAdapter = new FamilyAdapter(getActivity(), fly, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(family);
        listView.setAdapter(flyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Family pos = fly.get(position);
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


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseResources();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseResources();
    }

    public void releaseResources() {
        if (player != null) {
            player.release();
            player = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }

}
