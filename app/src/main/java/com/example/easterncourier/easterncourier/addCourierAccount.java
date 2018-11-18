package com.example.easterncourier.easterncourier;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_courier_account);

        databaseCourierAccount = FirebaseDatabase.getInstance().getReference("Courier Accounts");
        databaseCourierAccountLocation=FirebaseDatabase.getInstance().getReference("Courier Accounts Location");
        mStorageRef= FirebaseStorage.getInstance().getReference("Courier Accounts");
        mAuth = FirebaseAuth.getInstance();
        courierFirstName=findViewById(R.id.firstNameEditText);
        courierLastName=findViewById(R.id.lastNameEditText);
        courierAddress=findViewById(R.id.streetEditText);
        courierPhoneNumber=findViewById(R.id.phoneNumberEditText);
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

                registerUser();
                /*if (!TextUtils.isEmpty(courierFirstName.getText().toString()) &&
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

                }*/
            }
        });

    }



    private void registerUser() {
        //progressBar.setVisibility(View.VISIBLE);
        final String firstName = courierFirstName.getText().toString().trim(), lastName=courierLastName.getText().toString().trim()
                ,password=courierPassword.getText().toString().trim(),phone=courierPhoneNumber.getText().toString().trim();
        final String email = courierUserName.getText().toString().trim();
        //String password = courierPassword.getText().toString().trim();






        if (firstName.isEmpty()) {
            courierFirstName.setError("This Field is required!!");
            courierFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            courierLastName.setError("This Field is required!!");
            courierLastName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            courierUserName.setError("Invalid Email!!!");
            courierUserName.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            courierPassword.setError("This field is required!!");
            courierPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            courierPassword.setError("Please Enter more than 6 character for password!!!");
            courierPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            courierPhoneNumber.setError("This field is required!!");
            courierPhoneNumber.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            courierPhoneNumber.setError("Invalid Phone Number!!");
            courierPhoneNumber.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()){
                    uploadFile();
                    final addCourierAccountItem addCourierAccountItem=new addCourierAccountItem(
                            mAuth.getCurrentUser().getUid(),
                            firstName,
                            lastName,
                            courierAddress.getText().toString(),
                            courierBirthDate.getText().toString(),
                            courierPhoneNumber.getText().toString(),
                            " ",
                            " ",
                            " ",
                            mImageUri+" ",
                            courierUserName.getText().toString(),
                            courierPassword.getText().toString()

                    );

                    FirebaseDatabase.getInstance().getReference("Courier Accounts")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(addCourierAccountItem)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()){


                                        GeoFire geoFire= new GeoFire(databaseCourierAccountLocation);
                                        geoFire.setLocation(FirebaseAuth.getInstance().getCurrentUser().getUid(),new GeoLocation(0.00,0.00));
                                        Toast.makeText(addCourierAccount.this,"New Courier Account Added",Toast.LENGTH_LONG).show();
                                        AlertDialog.Builder builder=new AlertDialog.Builder(addCourierAccount.this);
                                        builder.setMessage("Registered Successfully!!!")
                                                .create().show();
                                        //Intent intent=new Intent(register_client_account.this,SignIn.class);
                                        //startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(addCourierAccount.this,"SignUp Failed!!!",Toast.LENGTH_LONG);
                                    }
                                }
                            });



                }
                else {
                    //Toast.makeText(register_client_account.this,task.getException().getMessage(),Toast.LENGTH_LONG);
                    AlertDialog.Builder builder=new AlertDialog.Builder(addCourierAccount.this);
                    builder.setMessage(task.getException().getMessage()).setNegativeButton("Retry",null)
                            .create().show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }*/
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
