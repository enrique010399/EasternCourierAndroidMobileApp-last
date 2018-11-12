package com.example.easterncourier.easterncourier;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_admin_requests extends RecyclerView.Adapter<Adapter_admin_requests.myViewHolder> {
    Context mContextAdminRequests;
    List<admin_request_item> mDataAdminRequests;
    ArrayList<admin_request_item> clientRequests;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }





    public Adapter_admin_requests(Context mContextAdminRequests, ArrayList<admin_request_item> clientRequests) {
        this.mContextAdminRequests = mContextAdminRequests;
        this.clientRequests = clientRequests;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater=LayoutInflater.from(mContextAdminRequests);
        //View v=inflater.inflate(R.layout.activity_admin_requests,parent,false);
        return new myViewHolder(LayoutInflater.from(mContextAdminRequests).inflate(R.layout.card_admin_request,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        //holder.clientImage.setImageResource(clientRequests.get(position).get());
        //Picasso.get().load(clientRequests.get(position).getClientPicture()).into(holder.clientImage);
        holder.clientUserName.setText(clientRequests.get(position).getClientFullName());
        holder.clientTimeRequest.setText(clientRequests.get(position).getClientDateRequested());

    }

    @Override
    public int getItemCount() {
        return clientRequests.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView clientImage;
        TextView clientUserName,clientTimeRequest;
        public myViewHolder(View itemView) {
            super(itemView);
            clientImage=itemView.findViewById(R.id.clientPictureRequest);
            clientUserName=itemView.findViewById(R.id.clientUserNameRequest);
            clientTimeRequest=itemView.findViewById(R.id.clientRequestTime);

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
