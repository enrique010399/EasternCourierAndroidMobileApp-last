package com.example.easterncourier.easterncourier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_admin_choose_courier extends RecyclerView.Adapter<Adapter_admin_choose_courier.myViewHolder> {

    Context mContextAdminChooseCourier;
    List<adminChooseCourierItem> mDataAdminChooseCourier;
    ArrayList<adminChooseCourierItem> chooseCourier;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public Adapter_admin_choose_courier(Context mContextAdminChooseCourier, ArrayList<adminChooseCourierItem> chooseCourier) {
        this.mContextAdminChooseCourier = mContextAdminChooseCourier;
        this.chooseCourier = chooseCourier;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new myViewHolder(LayoutInflater.from(mContextAdminChooseCourier).inflate(R.layout.card_admin_choose_courier, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        String FullName=chooseCourier.get(i).getCourierFirstName()+" "+chooseCourier.get(i).getCourierLastName();
        Picasso.get().load(chooseCourier.get(i).getCourierImage()).into(myViewHolder.courierImage);
        myViewHolder.courierFullName.setText(FullName);
        myViewHolder.courierUserName.setText(chooseCourier.get(i).getCourierUserName());
    }

    @Override
    public int getItemCount() {
            return chooseCourier.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView courierImage;
        TextView courierFullName,courierUserName;
        public myViewHolder(View itemView) {
            super(itemView);
            courierImage=itemView.findViewById(R.id.chooseCourierImage1);
            courierFullName=itemView.findViewById(R.id.chooseCourierFullName);
            courierUserName=itemView.findViewById(R.id.chooseCourierUserName);

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
