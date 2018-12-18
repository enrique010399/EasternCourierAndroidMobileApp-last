package com.example.easterncourier.easterncourier;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class admin_choose_transaction_dialog extends AppCompatDialogFragment {
    private Button clientButton,courierButton;
    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_admin_transaction_history,null);
        clientButton=view.findViewById(R.id.transactionClientBtn);
        courierButton=view.findViewById(R.id.transactionCourierBtn);
        builder.setView(view)
                .setTitle("Transaction History")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.pickButton("Clients");

            }
        });


        courierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.pickButton("Couriers");
            }
        });
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            listener=(ExampleDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface ExampleDialogListener{
        void pickButton(String accountType);
    }
}
