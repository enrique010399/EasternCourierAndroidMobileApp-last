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

public class Adapter_admin_messages extends RecyclerView.Adapter<Adapter_admin_messages.myViewHolder>{
    Context mContextAdminMessages;
    List<admin_messages_item> mDataAdminMessages;

    public Adapter_admin_messages(Context mContextAdminMessages, List<admin_messages_item> mDataAdminMessages) {
        this.mContextAdminMessages = mContextAdminMessages;
        this.mDataAdminMessages = mDataAdminMessages;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContextAdminMessages);
        View v=inflater.inflate(R.layout.activity_admin_messages,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.accountImage.setImageResource(mDataAdminMessages.get(position).getAccountImage());
        holder.adminAccountName.setText(mDataAdminMessages.get(position).getAccountName());
        holder.accountLMessage.setText(mDataAdminMessages.get(position).getAccountLMessages());
    }

    @Override
    public int getItemCount() {
        return mDataAdminMessages.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView accountImage;
        TextView adminAccountName,accountLMessage;
        public myViewHolder(View itemView) {
            super(itemView);
            accountImage=itemView.findViewById(R.id.accountImage);
            adminAccountName=itemView.findViewById(R.id.adminAccountName);
            accountLMessage=itemView.findViewById(R.id.accountLMessage);
        }
    }
}
