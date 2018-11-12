package com.example.easterncourier.easterncourier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_notification1 extends RecyclerView.Adapter<Adapter_notification1.myViewHolder>{
    Context mContextClientNotification1;
    ArrayList<addCourierAccountItem> clientNotification1;



    public Adapter_notification1(Context mContextClientNotification1, ArrayList<addCourierAccountItem> clientNotification1) {
        this.mContextClientNotification1 = mContextClientNotification1;
        this.clientNotification1 = clientNotification1;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(mContextClientNotification1).inflate(R.layout.card_notification1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.notificationCourierName.setText("Your Courier "+clientNotification1.get(position).getCourierFirstName()+" "+clientNotification1.get(position).getCourierLastName()+" is on the way!!!");
    }

    @Override
    public int getItemCount() {
        return clientNotification1.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView notificationCourierName,notificationTimeAdminApprove;
        public myViewHolder(View itemView) {
            super(itemView);

            notificationCourierName=itemView.findViewById(R.id.notificationCourierName);
            //notificationReceiverName=itemView.findViewById(R.id.notificationReceiverName);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position= getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }
}

