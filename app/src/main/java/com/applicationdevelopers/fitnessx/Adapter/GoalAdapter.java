package com.applicationdevelopers.fitnessx.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applicationdevelopers.fitnessx.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GoalAdapter  extends RecyclerView.Adapter<GoalAdapter.ViewHolderClass>{
    List<Drawable> list=new ArrayList<>();
    Context mcontext;
    public GoalAdapter(Context context, List<Drawable> mlist){
        mcontext=context;
        list=mlist;
    }
    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        View view= layoutInflater.inflate(R.layout.goal_item_list,parent,false);

        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(GoalAdapter.ViewHolderClass holder, int position) {
        holder.imageView.setImageDrawable(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

 public class ViewHolderClass extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolderClass( View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.goal_imageivew);
        }
    }
}
