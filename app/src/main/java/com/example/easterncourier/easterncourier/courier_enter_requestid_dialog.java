package com.example.easterncourier.easterncourier;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class courier_enter_requestid_dialog extends AppCompatDialogFragment  {
    private EditText requestIdEt;
    private ExampleDialogListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_enter_requestid,null);
        builder.setView(view)
                .setTitle("Enter Request Id")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String requestId=requestIdEt.getText().toString();
                        listener.applyTexts(requestId);

                    }
                });
        requestIdEt=view.findViewById(R.id.requestIdEt);
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
        void applyTexts(String requestId);
    }


}
