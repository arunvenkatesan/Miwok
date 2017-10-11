package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorId;

    public WordAdapter(Activity context, ArrayList<Word> words, int color)
    {

        super(context, 0, words);
        mColorId = color;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        Word currwordAdapter = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.text1);
        nameTextView.setText(currwordAdapter.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.text2);
        numberTextView.setText(currwordAdapter.getEnglishTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(currwordAdapter.hasImage()){
            imageView.setImageResource(currwordAdapter.getResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }


        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorId);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }



}