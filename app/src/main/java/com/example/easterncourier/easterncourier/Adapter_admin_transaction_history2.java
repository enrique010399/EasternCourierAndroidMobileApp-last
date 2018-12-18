package com.example.easterncourier.easterncourier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter_admin_transaction_history2 extends RecyclerView.Adapter<Adapter_admin_transaction_history2.myViewHolder>{
    Context mContextAdminTransactionHistory;
    List<admin_request_item> mDataAdminTransactionHistory;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public Adapter_admin_transaction_history2(Context mContextAdminTransactionHistory, List<admin_request_item> mDataAdminTransactionHistory) {
        this.mContextAdminTransactionHistory = mContextAdminTransactionHistory;
        this.mDataAdminTransactionHistory = mDataAdminTransactionHistory;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(mContextAdminTransactionHistory).inflate(R.layout.card_admin_transaction_history,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        //holder.transactionHistoryClientImage.setImageResource(mDataAdminTransactionHistory.get(position).getTransactionHistoryImage());
        holder.transactionFullName.setText(mDataAdminTransactionHistory.get(position).getRequestAssignedCourierFullName());
        holder.transactionDate.setText(mDataAdminTransactionHistory.get(position).getRequestTransactionDate());
        holder.transactionRequestId.setText(mDataAdminTransactionHistory.get(position).getRequestId());

    }


    @Override
    public int getItemCount() {
        return mDataAdminTransactionHistory.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView transactionFullName,transactionDate,transactionRequestId;
        public myViewHolder(View itemView) {
            super(itemView);
            transactionFullName=itemView.findViewById(R.id.transactionFullName);
            transactionDate=itemView.findViewById(R.id.transactionDate);
            transactionRequestId=itemView.findViewById(R.id.transactionRequestId);

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
