package com.applicationdevelopers.fitnessx.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applicationdevelopers.fitnessx.Model.NotificationModel;
import com.applicationdevelopers.fitnessx.R;

import java.util.ArrayList;
import java.util.List;

public class Notification extends RecyclerView.Adapter<Notification.ViewHolderClass>{
    Context context;
    List<NotificationModel> list=new ArrayList<>();
    public Notification(Context mcontext, List<NotificationModel> mlist){
        context=mcontext;
        list=mlist;
    }
    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item_list_layout_notification,parent,false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(Notification.ViewHolderClass holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderClass extends RecyclerView.ViewHolder{
        ImageView iconimage;
        TextView title,time;
        public ViewHolderClass(View itemView) {
            super(itemView);
            iconimage=itemView.findViewById(R.id.notification_image_item);
            title=itemView.findViewById(R.id.notification_titleview);
            time=itemView.findViewById(R.id.time_textview_notification);
        }
    }
}
