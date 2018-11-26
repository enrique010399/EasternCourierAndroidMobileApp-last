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

public class sigin_enter_verification_code extends AppCompatDialogFragment {
    private EditText verificationCodeEt;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.signin_enter_verfication_code,null);
        builder.setView(view)
                .setTitle("Enter Verification Code")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String verificationCode=verificationCodeEt.getText().toString();
                        listener.applyTexts(verificationCode);
                    }
                });
        verificationCodeEt=view.findViewById(R.id.verificationCodeEt1);
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
        void applyTexts(String verificationCode);
    }
}
