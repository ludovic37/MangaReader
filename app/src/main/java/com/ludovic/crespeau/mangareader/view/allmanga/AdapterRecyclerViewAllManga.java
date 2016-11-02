package com.ludovic.crespeau.mangareader.view.allmanga;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.model.MangaList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crespeau on 02/11/2016.
 */

public class AdapterRecyclerViewAllManga extends RecyclerView.Adapter<AdapterRecyclerViewAllManga.DataObjectHolder>{

    private List<MangaList> mangaLists;
    private Context context;

    private MyClickListener myClickListener;

    public AdapterRecyclerViewAllManga(Context context) {
        this.mangaLists = new ArrayList<>();
        this.context = context;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_all_manga, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.textViewName.setText(mangaLists.get(position).name);

        if(mangaLists.get(position).info!= null)
            if (mangaLists.get(position).info.length()>=100){
                String info = mangaLists.get(position).info.substring(0,100)+"...";
                holder.textViewInfos.setText(info);
            }
            else{
                holder.textViewInfos.setText(mangaLists.get(position).info);
            }

        Picasso.with(context)
                .load(mangaLists.get(position).cover)
                .fit()
                //.centerCrop()
                .into(holder.imageViewCover);
    }

    @Override
    public int getItemCount() {
        return mangaLists.size();
    }

    public void setData(List<MangaList> datas) {
        mangaLists = datas;
        Log.d("Tag","MAJ");
        notifyDataSetChanged();
    }

    public void addData(List<MangaList> userSmallsList) {
        int begin = mangaLists.size();
        mangaLists.addAll(userSmallsList);
        int end = mangaLists.size();
        for(int i = begin; i < end; i++)
            notifyItemInserted(i);
    }

    public void delData(int position){
        mangaLists.remove(position);
        notifyDataSetChanged();
    }


    public interface MyClickListener {
        void onItemClick(MangaList mangaList, View v);
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewCover;
        TextView textViewName;
        TextView textViewInfos;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imageViewCover = (ImageView) itemView.findViewById(R.id.imageViewCover);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewInfos = (TextView) itemView.findViewById(R.id.textViewInfos);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(mangaLists.get(getAdapterPosition()), v);
        }
    }
}