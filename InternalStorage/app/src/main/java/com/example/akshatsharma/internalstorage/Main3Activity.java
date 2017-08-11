package com.example.akshatsharma.internalstorage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    private String uid, currentUserEmail;

    EditText name, emailID, organization, loc, phnNumber;
    Button submitBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private final static String TAG = "Main3Activity";

    //private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        name = (EditText)findViewById(R.id.nameEditText);
        emailID = (EditText)findViewById(R.id.emailEditText);
        organization = (EditText)findViewById(R.id.orgEditText);
        loc = (EditText)findViewById(R.id.locEditText);
        phnNumber = (EditText)findViewById(R.id.phnEditText);
        submitBtn = (Button)findViewById(R.id.submitBtn);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    uid = user.getUid();
                    //currentUserEmail = user.getUid();

                    //Log.d(TAG, "current user email="+currentUserEmail);
                    //Log.d(TAG, user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);


/*        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uid = user.getUid();
            Log.d(TAG, "createUserWithEmail:onComplete:" + uid);
        }*/

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String email = emailID.getText().toString();
                String company = organization.getText().toString();
                String location = loc.getText().toString();
                String contact = phnNumber.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("userData");

                myRef.child(uid).child("name").setValue(userName);
                myRef.child(uid).child("company").setValue(company);
                myRef.child(uid).child("email").setValue(email);
                myRef.child(uid).child("location").setValue(location);
                myRef.child(uid).child("contact").setValue(contact);
/*                myRef.child(currentUserEmail).child("name").setValue(userName);
                myRef.child(currentUserEmail).child("company").setValue(company);
                myRef.child(currentUserEmail).child("email").setValue(email);
                myRef.child(currentUserEmail).child("location").setValue(location);
                myRef.child(currentUserEmail).child("contact").setValue(contact);*/

                /*Intent intent = new Intent(Main3Activity.this,Main4Activity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);*/

                Intent intent = new Intent(Main3Activity.this,Main5Activity.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });
    }
}
