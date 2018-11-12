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

public class admin_enter_bill_dialog extends AppCompatDialogFragment {
    EditText billEt;
    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_enter_bill,null);
        builder.setView(view)
                .setTitle("Enter Bill for the request")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String bill=billEt.getText().toString();
                        listener.processBill(Integer.parseInt(bill));

                    }
                });
        billEt=view.findViewById(R.id.billEt);
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener=(admin_enter_bill_dialog.ExampleDialogListener) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public interface ExampleDialogListener{
        void processBill(Integer bill);
    }
}
