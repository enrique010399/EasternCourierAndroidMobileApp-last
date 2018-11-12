package com.example.easterncourier.easterncourier;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class courier_enter_payment_dialog extends AppCompatDialogFragment {
    EditText cashEt;

    private ExampleDialogListener listener;

    TextView billTv;
    //public String bill2;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_enter_payment,null);

        billTv=view.findViewById(R.id.billTv);
        //billTv.setText(bill2);

        builder.setView(view)
                .setTitle("Enter Payment")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String cash=cashEt.getText().toString();
                        listener.processPayment(Integer.parseInt(cash));

                    }
                });
        cashEt=view.findViewById(R.id.cashEt);
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(courier_enter_payment_dialog.ExampleDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public interface ExampleDialogListener{
        void processPayment(Integer cash);
    }
}
