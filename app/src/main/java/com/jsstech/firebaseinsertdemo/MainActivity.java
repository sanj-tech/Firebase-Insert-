package com.jsstech.firebaseinsertdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edt_name,edt_email,edt_contact,edt_password,edt_address;
    private Button btn_send;

   DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name=findViewById(R.id.et_name);
        edt_email=findViewById(R.id.et_email);
        edt_contact=findViewById(R.id.et_contact);
        edt_address=findViewById(R.id.et_address);
        edt_password=findViewById(R.id.et_password);

        databaseReference=FirebaseDatabase.getInstance().getReference("User");

        btn_send=findViewById(R.id.bt_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NAME=edt_name.getText().toString().trim();
                String EMAIL=edt_email.getText().toString().trim();
                String CONTACT=edt_contact.getText().toString().trim();
                String PASSWORD=edt_password.getText().toString().trim();
                String ADDRESS=edt_address.getText().toString().trim();

                String ID=databaseReference.push().getKey();
                FirebaseModel firebaseModel=new FirebaseModel(ID,NAME,EMAIL,PASSWORD,CONTACT,ADDRESS);

                databaseReference.child(ID).setValue(firebaseModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"user insert sucessfully",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });






    }
}