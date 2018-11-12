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

public class Adapter_admin_couriers extends RecyclerView.Adapter<Adapter_admin_couriers.myViewHolder>{
    Context mContextAdminCourier;
    List<admin_couriers_item> mDataAdminCourier;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public Adapter_admin_couriers(Context mContextAdminCourier, List<admin_couriers_item> mDataAdminCourier) {
        this.mContextAdminCourier = mContextAdminCourier;
        this.mDataAdminCourier = mDataAdminCourier;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(mContextAdminCourier).inflate(R.layout.card_admin_couriers,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        //holder.courierPicture.setImageResource(mDataAdminCourier.get(position).getCourierPicture());
        holder.courierFullName.setText(mDataAdminCourier.get(position).getCourierFirstName()+" "+mDataAdminCourier.get(position).getCourierLastName());
        holder.courierAddress.setText(mDataAdminCourier.get(position).getCourierAddress());
    }

    @Override
    public int getItemCount() {
        return mDataAdminCourier.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView courierImage;
        TextView courierFullName,courierAddress;
        public myViewHolder(View itemView) {
            super(itemView);
            courierImage=itemView.findViewById(R.id.courierImage);
            courierFullName=itemView.findViewById(R.id.courierFullName);
            courierAddress=itemView.findViewById(R.id.courierAddress);

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
