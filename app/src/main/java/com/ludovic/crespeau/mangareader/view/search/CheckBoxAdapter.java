package com.ludovic.crespeau.mangareader.view.search;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.model.Genre;

import java.util.List;


/**
 * Created by Ludovic on 15/07/16.
 */
public class CheckBoxAdapter extends BaseAdapter{

    private Activity context;

    public CheckBoxAdapter(Activity context, List <Genre> packageList){
        super();
        this.context = context;
    }

    private class ViewHolder {
        CheckBox ck1;
    }

    public int getCount() {
        return SearchActivity.mListInterest.size();
    }

    public Object getItem(int position) {
        return SearchActivity.mListInterest.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_genre, null);
            holder = new ViewHolder();

            holder.ck1 = (CheckBox) convertView.findViewById(R.id.checkBoxGenre);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.ck1.setText(SearchActivity.mListInterest.get(position).genreId);
        holder.ck1.setChecked(SearchActivity.mListInterest.get(position).checked);

        //Log.d("TAG",SearchActivity.mListInterest.get(position).genreId + " " + SearchActivity.mListInterest.get(position).checked);


        holder.ck1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.d("AVANT",SearchActivity.mListInterest.get(position).checked+"");
                SearchActivity.mListInterest.get(position).checked = !SearchActivity.mListInterest.get(position).checked;
                //Log.d("APRES",SearchActivity.mListInterest.get(position).checked+"");
            }
        });

        return convertView;

    }

}
