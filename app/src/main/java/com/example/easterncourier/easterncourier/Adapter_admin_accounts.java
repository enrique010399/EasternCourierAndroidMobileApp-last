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
    List<registerClientRequest> mDataAdminAccounts;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public Adapter_admin_accounts(Context mContextAdminAccounts, List<registerClientRequest> mDataAdminAccounts) {
        this.mContextAdminAccounts = mContextAdminAccounts;
        this.mDataAdminAccounts = mDataAdminAccounts;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new myViewHolder(LayoutInflater.from(mContextAdminAccounts).inflate(R.layout.card_admin_accounts,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        //holder.accountPicture.setImageResource(mDataAdminAccounts.get(position).getAccountPicture());
        holder.accountClientFullName.setText(mDataAdminAccounts.get(position).getAccountFirstName()+" "+mDataAdminAccounts.get(position).getAccountLastName());
        holder.accountClientAddress.setText(mDataAdminAccounts.get(position).getAccountAddressStreet()+" "+
                mDataAdminAccounts.get(position).getAccountAddressBarangay()+" "+
                mDataAdminAccounts.get(position).getAccountAddressCity()+" "+mDataAdminAccounts.get(position).getAccountAddressProvince());
    }

    @Override
    public int getItemCount() {
        return  mDataAdminAccounts.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView accountClientPicture;
        TextView accountClientFullName,accountClientAddress;
        public myViewHolder(View itemView) {
            super(itemView);
            accountClientPicture=itemView.findViewById(R.id.accountClientImage);
            accountClientFullName=itemView.findViewById(R.id.accountClientFullName);
            accountClientAddress=itemView.findViewById(R.id.accountClientAddress);

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
