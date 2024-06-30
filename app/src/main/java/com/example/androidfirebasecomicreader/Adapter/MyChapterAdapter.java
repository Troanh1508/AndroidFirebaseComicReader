package com.example.androidfirebasecomicreader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfirebasecomicreader.Chapters;
import com.example.androidfirebasecomicreader.Common;
import com.example.androidfirebasecomicreader.Model.Chapter;
import com.example.androidfirebasecomicreader.R;
import com.example.androidfirebasecomicreader.RecyclerItemClickListener;
import com.example.androidfirebasecomicreader.ViewComic;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {
    Context context;
    List<Chapter> chapterList;
    LayoutInflater inflater;

    public MyChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.chapter_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txt_chapter_number.setText(chapterList.get(position).Name);

        holder.setRecyclerItemClickListener(new RecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.chapterSelected = chapterList.get(position);
                Common.chapterIndex = position;

                Intent intent = new Intent(context, ViewComic.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_chapter_number;
        RecyclerItemClickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_chapter_number = (TextView)itemView.findViewById(R.id.txt_chapter_number);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            recyclerItemClickListener.onClick(v,getAdapterPosition());
        }
    }

}
