package com.ludovic.crespeau.mangareader.view.favoris;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ludovic.crespeau.mangareader.R;
import com.ludovic.crespeau.mangareader.model.MangaId;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crespeau on 04/11/2016.
 */

public class AdapterRecyclerViewFavoris extends RecyclerView.Adapter<AdapterRecyclerViewFavoris.DataObjectHolder> {

    private List<MangaId> mangaLists;
    private Context context;

    private MyClickListener myClickListener;

    public AdapterRecyclerViewFavoris(Context context) {
        this.mangaLists = new ArrayList<>();
        this.context = context;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favoris, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {


        if (mangaLists != null) {

            holder.textViewName.setText(mangaLists.get(position).manga.name);
            holder.textViewYear.setText("Year: " + mangaLists.get(position).manga.yearOfRelease + "");
            holder.textViewStatus.setText("Status: " + mangaLists.get(position).manga.status);

            List<String> authorList = mangaLists.get(position).manga.author;
            List<String> artistList = mangaLists.get(position).manga.artist;
            List<String> genresList = mangaLists.get(position).manga.genres;
            String author = "Author: ";
            String artist = "Artist: ";
            String genres = "Genres: ";

            for (int i = 0; i < authorList.size(); i++) {
                author += authorList.get(i) + ", ";
            }
            for (int i = 0; i < artistList.size(); i++) {
                artist += artistList.get(i) + ", ";
            }
            for (int i = 0; i < genresList.size(); i++) {
                genres += genresList.get(i) + ", ";
            }

            holder.textViewAuthor.setText(mangaLists.get(position).manga.author.toString());
            holder.textViewAuthor.setText(author);
            holder.textViewArtist.setText(artist);
            holder.textViewGenre.setText(genres);


            Picasso.with(context)
                    .load(mangaLists.get(position).manga.cover)
                    .error(R.drawable.no_image_available_large)
                    .into(holder.imageViewCover);
        }



    }

    @Override
    public int getItemCount() {
        return mangaLists.size();
    }

    public void setData(List<MangaId> datas) {
        mangaLists = datas;
        Log.d("Tag","MAJ");
        notifyDataSetChanged();
    }

    public void addData(List<MangaId> userSmallsList) {
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
        void onItemClick(MangaId mangaList, View v);
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewCover;
        TextView textViewName;
        TextView textViewYear;
        TextView textViewAuthor;
        TextView textViewArtist;
        TextView textViewGenre;
        TextView textViewStatus;
        //TextView textViewInfos;

        public DataObjectHolder(View itemView) {
            super(itemView);
            imageViewCover = (ImageView) itemView.findViewById(R.id.imageViewCoverFavoris);
            textViewName = (TextView) itemView.findViewById(R.id.textViewNameFavoris);
            textViewYear = (TextView) itemView.findViewById(R.id.textViewYearFavoris);
            textViewArtist = (TextView) itemView.findViewById(R.id.textViewArtistFavoris);
            textViewAuthor = (TextView) itemView.findViewById(R.id.textViewAuthorFavoris);
            textViewGenre = (TextView) itemView.findViewById(R.id.textViewGenreFavoris);
            textViewStatus = (TextView) itemView.findViewById(R.id.textViewStatusFavoris);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(mangaLists.get(getAdapterPosition()), v);
        }
    }

}
