package com.applicationdevelopers.fitnessx.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applicationdevelopers.fitnessx.Interfaces.RecyclerviewDataPass;
import com.applicationdevelopers.fitnessx.R;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FitnessGIFAdapter extends RecyclerView.Adapter<FitnessGIFAdapter.ViewHolderClass> {
    Context mcontext;
    List<Drawable> mlistdrawable = new ArrayList();
    RecyclerviewDataPass recyclerviewDataPass;
    public FitnessGIFAdapter(Context context,List<Drawable> mdraw,RecyclerviewDataPass mrecyclerviewDataPass) {
        mcontext = context;
        mlistdrawable=mdraw;
        recyclerviewDataPass=mrecyclerviewDataPass;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        View view=layoutInflater.inflate(R.layout.item_list_layout_notification,parent,false);
        return new ViewHolderClass(view);    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FitnessGIFAdapter.ViewHolderClass holder, int position) {
        Glide.with(mcontext).load(mlistdrawable.get(position)).into(holder.iconimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mcontext, ""+position, Toast.LENGTH_SHORT).show();
                recyclerviewDataPass.pass(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mlistdrawable.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView iconimage;
        public ViewHolderClass(@NonNull @NotNull View itemView) {
            super(itemView);

            iconimage=itemView.findViewById(R.id.notification_image_item);

        }
    }
}
