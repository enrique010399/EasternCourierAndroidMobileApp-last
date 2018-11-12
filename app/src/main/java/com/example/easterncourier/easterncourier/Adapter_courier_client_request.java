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

public class Adapter_courier_client_request extends RecyclerView.Adapter<Adapter_courier_client_request.myViewHolder> {

    Context mContextCourierClientRequest;
    ArrayList<admin_request_item> courierClientRequest;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public Adapter_courier_client_request(Context mContextCourierClientRequest, ArrayList<admin_request_item> chooseCourier) {
        this.mContextCourierClientRequest = mContextCourierClientRequest;
        this.courierClientRequest = chooseCourier;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(mContextCourierClientRequest).inflate(R.layout.card_courier_client_request, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        //Picasso.get().load(chooseCourier.get(i).getCourierImage()).into(myViewHolder.courierImage);
        //myViewHolder.courierFullName.setText(FullName);
        //myViewHolder.courierUserName.setText(chooseCourier.get(i).getCourierUserName());

        holder.clientFullName.setText(courierClientRequest.get(position).getClientFullName().toString());
        holder.clientTimeRequested.setText(courierClientRequest.get(position).getClientDateRequested().toString());
    }

    @Override
    public int getItemCount() {
        return courierClientRequest.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView clientImage;
        TextView clientFullName,clientTimeRequested;
        public myViewHolder(View itemView) {
            super(itemView);
            clientImage=itemView.findViewById(R.id.courierClientPictureRequest);
            clientFullName=itemView.findViewById(R.id.couruerClientFullNameRequest);
            clientTimeRequested=itemView.findViewById(R.id.courierClientRequestTime);

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
