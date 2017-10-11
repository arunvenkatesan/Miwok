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

public class ColorAdapter extends ArrayAdapter<Colors> {

    private int mColorId;
    public ColorAdapter(Activity context, ArrayList<Colors> colors,int colorId) {

        super(context, 0, colors);
        mColorId=colorId;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        Colors currColorAdapter = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.text1);
        nameTextView.setText(currColorAdapter.getMiwokColor());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.text2);
        numberTextView.setText(currColorAdapter.getDefaultColor());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(currColorAdapter.hasImage()) {
            imageView.setImageResource(currColorAdapter.getmResourceId());
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }


        View textContainer = (View) listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorId);
        textContainer.setBackgroundColor(color);

        return  listItemView;
    }
}
