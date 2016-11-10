package com.ludovic.crespeau.mangareader.view.chapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.model.Chapters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crespeau on 09/11/2016.
 */

public class AdapterRecyclerViewChapter extends RecyclerView.Adapter<AdapterRecyclerViewChapter.DataObjectHolder>{

    private List<Chapters> mangaLists;
    private Context context;

    private MyClickListener myClickListener;

    public AdapterRecyclerViewChapter(Context context) {
        this.mangaLists = new ArrayList<>();
        this.context = context;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chapter, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        if (mangaLists != null) {

            int numChapter = mangaLists.get(position).chapterId;
            String nameChapter;
            if (mangaLists.get(position).name != null)
                nameChapter = mangaLists.get(position).name;
            else
                nameChapter = " -";

            holder.textViewName.setText("Chapter "+numChapter+": "+ nameChapter);
        }
    }

    @Override
    public int getItemCount() {
        return mangaLists.size();
    }

    public void setData(List<Chapters> datas) {
        mangaLists = datas;
        Log.d("Tag","MAJ");
        notifyDataSetChanged();
    }


    public interface MyClickListener {
        void onItemClick(Chapters mangaList, View v);
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textViewName;

        public DataObjectHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewChapterName);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(mangaLists.get(getAdapterPosition()), v);
        }
    }
}
