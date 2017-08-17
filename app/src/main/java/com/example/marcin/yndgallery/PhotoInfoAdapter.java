package com.example.marcin.yndgallery;

/**
 * Created by Marcin on 17.08.2017.
 */

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import java.util.List;

class PhotoInfoAdapter extends ArrayAdapter<PhotoInfo> {
    Context mcontext;


    PhotoInfoAdapter(Context context, List<PhotoInfo> photoInfoList) {
        super(context, android.R.layout.simple_list_item_1, photoInfoList);

        mcontext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.photo_info, parent, false);

        TextView author = (TextView) row.findViewById(R.id.photoInfoAuthor);
        author.setText(getItem(position).getAuthor().toString());

        Integer no = position;
        TextView number = (TextView) row.findViewById(R.id.photoInfoNo);
        number.setText(no.toString());

        //TextView title = (TextView) row.findViewById(R.id.title);
        //title.setText(getItem(position).title);

        return(row);
    }
}