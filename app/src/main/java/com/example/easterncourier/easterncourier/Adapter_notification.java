package com.example.easterncourier.easterncourier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_notification extends RecyclerView.Adapter<Adapter_notification.myViewHolder>{
    Context mContextClientNotification;
    ArrayList<admin_request_item> clientNotification;

    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public Adapter_notification(Context mContextClientNotification, ArrayList<admin_request_item> clientNotification) {
        this.mContextClientNotification = mContextClientNotification;
        this.clientNotification = clientNotification;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(mContextClientNotification).inflate(R.layout.card_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.notificationReceiverName.setText("Your book request for "+clientNotification.get(position).getReceiverName().toString()+" just accepted!!");

    }

    @Override
    public int getItemCount() {
        return clientNotification.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView notificationReceiverName,notificationTimeAdminApprove;
        public myViewHolder(View itemView) {
            super(itemView);

            notificationTimeAdminApprove=itemView.findViewById(R.id.notificationTimeAdminApprove);
            notificationReceiverName=itemView.findViewById(R.id.notificationReceiverName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position= getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
