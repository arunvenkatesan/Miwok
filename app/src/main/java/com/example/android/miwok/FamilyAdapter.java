package com.example.android.miwok;

import android.app.Activity;
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

import static com.example.android.miwok.R.id.colors;

public class FamilyAdapter extends ArrayAdapter<Family> {

    private int mColorId;

    public FamilyAdapter(Activity context, ArrayList<Family> fly, int colorId) {

        super(context, 0, fly);
        mColorId=colorId;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        Family currFlyAdapter = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.text1);
        nameTextView.setText(currFlyAdapter.getmMiwokFamilyName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.text2);
        numberTextView.setText(currFlyAdapter.getmDefaultFamilyName());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(currFlyAdapter.hasImage()) {
            imageView.setImageResource(currFlyAdapter.getmResourceId());
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer =(View) listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}