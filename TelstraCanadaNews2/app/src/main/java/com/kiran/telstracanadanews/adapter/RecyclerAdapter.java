package com.kiran.telstracanadanews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kiran.telstracanadanews.R;
import com.kiran.telstracanadanews.models.Row;

import java.util.ArrayList;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Row> mNews = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    public void renderList(List<Row> canadaNew) {
        mNews.clear();
        mNews.addAll(canadaNew);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).mTextTitle.setText(mNews.get(i).getTitle());
        ((ViewHolder) viewHolder).mTextDescription.setText(mNews.get(i).getDescription());
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNews.get(i).getImageHref())
                .into(((ViewHolder) viewHolder).mImageHref);
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageHref;
        private TextView mTextTitle, mTextDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageHref = (ImageView) itemView.findViewById(R.id.imageHref);
            mTextTitle = (TextView) itemView.findViewById(R.id.textTitle);
            mTextDescription = (TextView) itemView.findViewById(R.id.textDescription);
        }
    }
}

