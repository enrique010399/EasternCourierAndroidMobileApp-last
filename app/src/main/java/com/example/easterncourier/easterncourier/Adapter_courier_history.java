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

public class Adapter_courier_history extends RecyclerView.Adapter<Adapter_courier_history.myViewHolder>{
    Context mContextCourierHistory;
    List<courier_history_item> mDataCourierHistory;

    public Adapter_courier_history(Context mContextCourierHistory, List<courier_history_item> mDataCourierHistory) {
        this.mContextCourierHistory = mContextCourierHistory;
        this.mDataCourierHistory = mDataCourierHistory;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContextCourierHistory);
        View v=inflater.inflate(R.layout.card_courier_history,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.courierHistoryClientPicture.setImageResource(mDataCourierHistory.get(position).getCourierHistoryClientPicture());
        holder.courierHistoryClientName.setText(mDataCourierHistory.get(position).getCourierHistoryClientName());
        holder.courierHistoryDeliveredDate.setText(mDataCourierHistory.get(position).getCourierHistoryDeliveredDate());
    }

    @Override
    public int getItemCount() {
        return mDataCourierHistory.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView courierHistoryClientPicture;
        TextView courierHistoryClientName,courierHistoryDeliveredDate;
        public myViewHolder(View itemView) {
            super(itemView);
            courierHistoryClientPicture=itemView.findViewById(R.id.courierHistoryClientPicture);
            courierHistoryClientName=itemView.findViewById(R.id.courierHistoryClientName);
            courierHistoryDeliveredDate=itemView.findViewById(R.id.courierHistoryDeliveredDate);
        }
    }
}
