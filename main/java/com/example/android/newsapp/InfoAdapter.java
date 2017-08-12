package com.example.android.newsapp;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class InfoAdapter extends BaseAdapter {

    private Context context;

    private List<NewsInfo> newsInfos;

    public InfoAdapter(Context context) {
        this.context = context;
        this.newsInfos = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return newsInfos.size();
    }

    @Override
    public NewsInfo getItem(int position) {
        return newsInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Typeface fonts1 = Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Light.ttf");

        Typeface fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Regular.ttf");

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list, null);

            viewHolder = new ViewHolder();
            viewHolder.webTitle = (MyTextView) convertView.findViewById(R.id.web_title);
            viewHolder.webPublicationDate = (MyTextView) convertView.findViewById(R.id.publication_date);
            viewHolder.sectionName = (MyTextView) convertView.findViewById(R.id.section_name);
            viewHolder.webUrl = (MyTextView) convertView.findViewById(R.id.weburl);

            viewHolder.webTitle.setTypeface(fonts1);
            viewHolder.webPublicationDate.setTypeface(fonts1);
            viewHolder.webTitle.setTypeface(fonts2);
            viewHolder.sectionName.setTypeface(fonts2);

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        NewsInfo item = getItem(position);

        viewHolder.webTitle.setText(item.getWebTitle());
        viewHolder.sectionName.setText(item.getSectionName());
        viewHolder.webPublicationDate.setText(item.getWebPublicationDate());
        viewHolder.webUrl.setText(item.getWebUrl());

        return convertView;
    }

    public void addFeeds(List<NewsInfo> feedArrayList) {
        this.newsInfos = feedArrayList;
    }

    public void clear() {
        newsInfos.clear();
    }

    private class ViewHolder {

        MyTextView sectionName;
        MyTextView webPublicationDate;
        MyTextView webTitle;
        MyTextView webUrl;
    }
}