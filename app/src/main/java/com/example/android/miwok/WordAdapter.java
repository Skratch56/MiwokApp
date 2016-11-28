package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.android.miwok.R.id.imgIcon;

/**
 * Created by CE on 2016-11-18.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    int resColor;

    public WordAdapter(Context context, List<Word> words,int color) {
        super(context, 0, words);
        resColor = color;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word words = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.listrow);
        linearLayout.setBackgroundResource(resColor);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
        int resID = words.getResID();

        if (words.hasImage()){
            imgIcon.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(resID).into(imgIcon);
        }else{
            imgIcon.setVisibility(View.GONE);
        }

        TextView txtDefault = (TextView) convertView.findViewById(R.id.txtDefault);
        txtDefault.setText(words.getDefaultTranslation());

        TextView txtMihok = (TextView) convertView.findViewById(R.id.txtMihok);
        txtMihok.setText(words.getMiwokTranslation());

        return convertView;

    }
}
