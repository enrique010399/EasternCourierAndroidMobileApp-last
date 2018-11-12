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

public class Adapter_courier_clients extends RecyclerView.Adapter<Adapter_courier_clients.myViewHolder> {

    Context mContextCourierClients;
    List<courier_clients_item> mDataCourierClients;

    public Adapter_courier_clients(Context mContextCourierClients, List<courier_clients_item> mDataCourierClients) {
        this.mContextCourierClients = mContextCourierClients;
        this.mDataCourierClients = mDataCourierClients;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContextCourierClients);
        View v=inflater.inflate(R.layout.card_courier_clients,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.courierClientPicture.setImageResource(mDataCourierClients.get(position).getCourierClientPicture());
        holder.courierClientName.setText(mDataCourierClients.get(position).getCourierClientName());
        holder.courierClientAssignedTime.setText(mDataCourierClients.get(position).getCourierClientTimeAssigned());
    }

    @Override
    public int getItemCount() {
        return mDataCourierClients.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView courierClientPicture;
        TextView courierClientName,courierClientAssignedTime;
        public myViewHolder(View itemView) {
            super(itemView);
            courierClientPicture=itemView.findViewById(R.id.courierClientPicture);
            courierClientName=itemView.findViewById(R.id.courierClientName);
            courierClientAssignedTime=itemView.findViewById(R.id.courierClientTimeAssigned);
        }
    }
}
