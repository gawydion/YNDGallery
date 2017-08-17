package com.example.marcin.yndgallery;

/**
 * Created by Marcin on 17.08.2017.
 */

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
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
        number.setText(getItem(position).getAuthorIndex().toString());

        //https://unsplash.it/50/50?image=0
        //http://square.github.io/picasso/

        ImageView imageView = (ImageView) row.findViewById(R.id.photoInfoImage);

        Picasso.with(mcontext).load("https://unsplash.it/100/100?image=" + position).into(imageView);

        return(row);
    }

    public static List<PhotoInfo> addAuthorIndex(List<PhotoInfo> photoInfoList){

        //https://stackoverflow.com/questions/9109890/android-java-how-to-sort-a-list-of-objects-by-a-certain-value-within-the-object
        //druga, trzecia odpowiedź

        Collections.sort(photoInfoList, new Comparator<PhotoInfo>(){
            public int compare(PhotoInfo obj1, PhotoInfo obj2) {
                // ## Ascending order
                return obj1.getAuthor().compareToIgnoreCase(obj2.getAuthor()); // To compare string values
                // return Integer.valueOf(obj1.empId).compareTo(obj2.empId); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(obj1.empId); // To compare integer values
            }
        });

        photoInfoList.get(0).setAuthorIndex(1);
        for(int i = 1; i<photoInfoList.size();i++){

            if(photoInfoList.get(i-1).getAuthor().equals(photoInfoList.get(i).getAuthor())){
                photoInfoList.get(i).setAuthorIndex(photoInfoList.get(i-1).getAuthorIndex()+1);
            }
            else{
                photoInfoList.get(i).setAuthorIndex(1);
            }

        }

        Collections.sort(photoInfoList, new Comparator<PhotoInfo>(){
            public int compare(PhotoInfo obj1, PhotoInfo obj2) {
                // ## Ascending order
                //return obj1.getAuthor().compareToIgnoreCase(obj2.getAuthor()); // To compare string values
                return Integer.valueOf(obj1.getId()).compareTo(obj2.getId()); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(obj1.empId); // To compare integer values
            }
        });

        return photoInfoList;
    }

}