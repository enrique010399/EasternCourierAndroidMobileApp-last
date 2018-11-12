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

public class Adapter_history extends RecyclerView.Adapter<Adapter_history.myViewHolder> {
    Context mContextHistory;
    List<history_item> mDataHistory;

    public Adapter_history(Context mContextHistory, List<history_item> mDataHistory) {
        this.mContextHistory = mContextHistory;
        this.mDataHistory = mDataHistory;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContextHistory);
        View v=inflater.inflate(R.layout.card_history,parent,false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.historyImage.setImageResource(mDataHistory.get(position).getHistoryImage());
        holder.historyType.setText(mDataHistory.get(position).getHistoryType());
        holder.historyTime.setText(mDataHistory.get(position).getHistoryTime());

    }

    @Override
    public int getItemCount() {
        return mDataHistory.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView historyImage;
        TextView historyType,historyTime;

        public myViewHolder(View itemView) {
            super(itemView);

            historyImage=itemView.findViewById(R.id.historyImage);
            historyTime=itemView.findViewById(R.id.historyTime);
            historyType=itemView.findViewById(R.id.historyType);


        }
    }

}
