package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhraseAdapter extends ArrayAdapter<Phrases> {

    private int mColorId;
    public PhraseAdapter(Context context, ArrayList<Phrases> phrases,int colorId) {
        super(context, 0, phrases);
         mColorId=colorId;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Phrases phraseAdapter = getItem(position);
        TextView textView= (TextView)listItemView.findViewById(R.id.text1);
        textView.setText(phraseAdapter.getmMiwokPhrase());

        TextView textView1 =(TextView) listItemView.findViewById(R.id.text2);
        textView1.setText(phraseAdapter.getmDefaultPhrase());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(phraseAdapter.hasImage()){
            imageView.setImageResource(phraseAdapter.getMimageFind());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer =(View) listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorId);
        textContainer.setBackgroundColor(color);

        return  listItemView;

    }
}