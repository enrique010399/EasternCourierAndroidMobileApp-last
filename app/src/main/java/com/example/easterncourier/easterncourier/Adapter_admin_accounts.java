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

public class Adapter_admin_accounts extends RecyclerView.Adapter<Adapter_admin_accounts.myViewHolder>{
    Context mContextAdminAccounts;
    List<admin_accounts_item> mDataAdminAccounts;

    public Adapter_admin_accounts(Context mContextAdminAccounts, List<admin_accounts_item> mDataAdminAccounts) {
        this.mContextAdminAccounts = mContextAdminAccounts;
        this.mDataAdminAccounts = mDataAdminAccounts;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContextAdminAccounts);
        View v=inflater.inflate(R.layout.activity_admin_accounts,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.accountPicture.setImageResource(mDataAdminAccounts.get(position).getAccountPicture());
        holder.accountName.setText(mDataAdminAccounts.get(position).getAccountName());
        holder.accountType.setText(mDataAdminAccounts.get(position).getAccountType());
    }

    @Override
    public int getItemCount() {
        return  mDataAdminAccounts.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView accountPicture;
        TextView accountName,accountType;
        public myViewHolder(View itemView) {
            super(itemView);
            accountPicture=itemView.findViewById(R.id.accountPicture);
            accountName=itemView.findViewById(R.id.accountName);
            accountType=itemView.findViewById(R.id.accountType);
        }
    }

}
