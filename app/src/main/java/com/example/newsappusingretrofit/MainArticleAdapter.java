package com.example.newsappusingretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {

    private List<Article> articleArrayList;
    private Context context;
    public MainArticleAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MainArticleAdapter.ViewHolder holder, int position) {
        final Article currentItem = articleArrayList.get(position);
        String imageUrl=currentItem.getUrlToImage();
        String title=currentItem.getTitle();
        String details=currentItem.getDescription();
        String author=currentItem.getAuthor();
        String publishedAt=currentItem.getPublishedAt();
        final String url=currentItem.getUrl();

        holder.mAuthor.setText(author);
        holder.mTitle.setText(title);
        holder.mPublishedAt.setText(publishedAt);
        holder.mDetails.setText(details);
        Picasso.get().load(imageUrl).into(holder.mUrlToImage);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView mUrlToImage;
        public TextView mTitle,mAuthor,mDetails,mPublishedAt;
        public CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUrlToImage=itemView.findViewById(R.id.urlToImage);
            mAuthor=itemView.findViewById(R.id.author);
            mDetails=itemView.findViewById(R.id.details);
            mTitle=itemView.findViewById(R.id.title);
            mPublishedAt=itemView.findViewById(R.id.publishedAt);
            mCardView=itemView.findViewById(R.id.cardView);
        }
    }

}
