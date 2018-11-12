package com.example.easterncourier.easterncourier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<notification_item> mData;


    public Adapter(Context mContext, List<notification_item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mContext);
        View v=inflater.inflate(R.layout.card_notification,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.notificationImage.setImageResource(mData.get(position).getNotificationImage());
        holder.notificationType.setText(mData.get(position).getNotificationType());
        holder.notficationTime.setText(mData.get(position).getNotificationTime());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView notificationImage;
        TextView notificationType,notficationTime;



        public myViewHolder(View itemView) {
            super(itemView);

            //notificationImage=itemView.findViewById(R.id.notificationImage);
            //notificationType=itemView.findViewById(R.id.notificationType);
            //notficationTime=itemView.findViewById(R.id.notificationTime);
        }
    }
}
