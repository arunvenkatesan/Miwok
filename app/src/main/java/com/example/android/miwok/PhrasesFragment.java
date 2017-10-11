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
public class PhrasesFragment extends Fragment{

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

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_phrases,container,false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final AudioManager.OnAudioFocusChangeListener amChange;

        final ArrayList<Phrases> phrases = new ArrayList<Phrases>();
        phrases.add(new Phrases("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        phrases.add(new Phrases("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrases.add(new Phrases("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrases.add(new Phrases("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new Phrases("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrases.add(new Phrases("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrases.add(new Phrases("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrases.add(new Phrases("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        phrases.add(new Phrases("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phrases.add(new Phrases("Come here.", "әnni'nem", R.raw.phrase_come_here));

        PhraseAdapter phraseAdapter = new PhraseAdapter(getActivity(), phrases, R.color.category_phrases);
        ListView listView = (ListView) rootView.findViewById(R.id.phrases);
        listView.setAdapter(phraseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Phrases pos = phrases.get(position);
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

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseResources();
        }
    };

}
