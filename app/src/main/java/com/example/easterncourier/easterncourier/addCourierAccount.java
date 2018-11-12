package com.example.easterncourier.easterncourier;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class addCourierAccount extends AppCompatActivity {
    EditText courierFirstName,courierLastName,courierAddress,courierPhoneNumber,courierBirthDate,courierUserName,courierPassword;
    ImageView courierImage;
    Button chooseCourierImageBtn,courierSubmitBtn;

    private static final int PICK_IMAGE_REQUEST=1;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    DatabaseReference databaseCourierAccount,databaseCourierAccountLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_courier_account);

        databaseCourierAccount = FirebaseDatabase.getInstance().getReference("Courier Accounts");
        databaseCourierAccountLocation=FirebaseDatabase.getInstance().getReference("Courier Accounts Location");
        mStorageRef= FirebaseStorage.getInstance().getReference("Courier Accounts");

        courierFirstName=findViewById(R.id.firstNameEditText);
        courierLastName=findViewById(R.id.lastNameEditText);
        courierAddress=findViewById(R.id.courierAddress);
        courierPhoneNumber=findViewById(R.id.courierPhoneNumber);
        courierUserName=findViewById(R.id.courierUserName);
        courierPassword=findViewById(R.id.courierPassword);


        courierImage=findViewById(R.id.courierImage);
        courierBirthDate=findViewById(R.id.courierBirthDate);

        chooseCourierImageBtn=findViewById(R.id.courierChooseImageBtn);
        courierSubmitBtn=findViewById(R.id.courierSubmitBtn);

        chooseCourierImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });




        courierSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(courierFirstName.getText().toString()) &&
                        !TextUtils.isEmpty(courierLastName.getText().toString()) &&
                        !TextUtils.isEmpty(courierAddress.getText().toString()) &&
                        !TextUtils.isEmpty(courierPhoneNumber.getText().toString())){

                    uploadFile();
                    String id=databaseCourierAccount.push().getKey();
                    addCourierAccountItem addCourierAccountItem1=new addCourierAccountItem(id,courierFirstName.getText().toString(),courierLastName.getText().toString(),
                            courierAddress.getText().toString(),courierBirthDate.getText().toString(),courierPhoneNumber.getText().toString(),
                            "not set","not set","none",mImageUri+"",courierUserName.getText().toString(),
                            courierPassword.getText().toString());
                    databaseCourierAccount.child(id).setValue(addCourierAccountItem1);
                    GeoFire geoFire= new GeoFire(databaseCourierAccountLocation);
                    geoFire.setLocation(id,new GeoLocation(0.00,0.00));
                    Toast.makeText(addCourierAccount.this,"New Courier Account Added",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private String getFilextension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){

        if (mImageUri != null) {
            final StorageReference imgReference = mStorageRef;
            UploadTask uploadTask = imgReference.putFile(mImageUri);

            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return imgReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri taskResult = task.getResult();
                        //BookUploadImage bookUploadImage = new BookUploadImage("package", taskResult.toString());
                        //mDatabaseRef.push().setValue(bookUploadImage);
                        Toast.makeText(addCourierAccount.this,"Uploaded Successfully",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri =data.getData();
            Picasso.get().load(mImageUri).into(courierImage);
        }

    }

}
