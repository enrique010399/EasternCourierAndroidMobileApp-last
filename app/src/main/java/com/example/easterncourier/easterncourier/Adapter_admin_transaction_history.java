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

public class Adapter_admin_transaction_history extends RecyclerView.Adapter<Adapter_admin_transaction_history.myViewHolder> {

    Context mContextAdminTransactionHistory;
    List<admin_transaction_history_item> mDataAdminTransactionHistory;

    public Adapter_admin_transaction_history(Context mContextAdminTransactionHistory, List<admin_transaction_history_item> mDataAdminTransactionHistory) {
        this.mContextAdminTransactionHistory = mContextAdminTransactionHistory;
        this.mDataAdminTransactionHistory = mDataAdminTransactionHistory;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContextAdminTransactionHistory);
        View v=inflater.inflate(R.layout.activity_transaction_history,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.transactionHistoryClientImage.setImageResource(mDataAdminTransactionHistory.get(position).getTransactionHistoryImage());
        holder.transactionHistoryClientName.setText(mDataAdminTransactionHistory.get(position).getTransactionHistoryClientName());
        holder.transactionHistoryDate.setText(mDataAdminTransactionHistory.get(position).getTransactionHistoryDate());
    }

    @Override
    public int getItemCount() {
        return mDataAdminTransactionHistory.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView transactionHistoryClientImage;
        TextView transactionHistoryClientName,transactionHistoryDate;
        public myViewHolder(View itemView) {
            super(itemView);
            transactionHistoryClientImage=itemView.findViewById(R.id.transactionHistoryClientImage);
            transactionHistoryClientName=itemView.findViewById(R.id.transactionHistoryClientName);
            transactionHistoryDate=itemView.findViewById(R.id.transactionHistoryDate);
        }
    }

}
