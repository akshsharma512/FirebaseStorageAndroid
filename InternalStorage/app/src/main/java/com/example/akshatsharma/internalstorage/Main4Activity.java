package com.example.akshatsharma.internalstorage;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String uid;
    String company;
    String contact;
    TextView name, phnNumber, loc, email, organization;
    private final static String TAG = "Main4Activity";
    List<String> lst;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Log.d(TAG, "Inside onCreate()");
        name = (TextView) findViewById(R.id.userNameTextView);
        phnNumber = (TextView) findViewById(R.id.contactTextView);
        loc = (TextView) findViewById(R.id.locationTextView);
        email = (TextView) findViewById(R.id.emailTextView);
        organization = (TextView) findViewById(R.id.companyTextView);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        /*mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("userData").child(uid);*/
        //Query query = myRef.orderByChild(uid);


        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {

                    company = (String) messageSnapshot.child("company").getValue();
                    contact = (String) messageSnapshot.child("contact").getValue();
                }

                Log.d(TAG, "Company is: " + company);
                Log.d(TAG, "Contact is: " + contact);
                organization.setText(company);
                phnNumber.setText(contact);

                *//*HashMap<String, Message> value = (HashMap<String, String>) dataSnapshot.getValue();
                String company = value.get("company").toString();
                String contact = value.get("contact").toString();

                //name.setText(value);*//*
            }*/

            /*@Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }*/
        // });

        /*myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                name.setText(value);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        /*mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                        //uid = user.getUid();
                        myRef = database.getReference("userData");
                        Log.d(TAG,"Listener="+ uid);
                        //currentUserEmail = user.getUid();

                        //Log.d(TAG, "current user email="+currentUserEmail);
                        //Log.d(TAG, user.getEmail());
                    } else {
                        // User is signed out
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                }
        });*/

        //Log.i(TAG, "Inside onCreate user id=" + this.uid);


        //DatabaseReference myRef = database.getReference("userData").child(uid);

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        lst = new ArrayList<String>();// Result will be held Here
        database = FirebaseDatabase.getInstance();

        Log.d(TAG, "inside onStart" + uid);

        myRef = database.getReference("userData").child(uid);

        Log.d(TAG, "Ref is " + myRef);

        //Details details = new Details();

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    lst.add(String.valueOf(dsp.getValue())); //add result into array list
                }
                Log.d(TAG, "List is="+lst);
                organization.setText(lst.get(0));
                phnNumber.setText(lst.get(1));
                email.setText(lst.get(2));
                loc.setText(lst.get(3));
                name.setText(lst.get(4));

                /*for(DataSnapshot data: dataSnapshot.getChildren()){

                    Details details = data.getValue(Details.class);
                    Log.d(TAG,"company="+ details.getCompanyName());
                }*/
                //String value = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        /*mAuth.addAuthStateListener(mAuthListener);
        Log.d(TAG,"Inside onStart user id=" +uid);
        Log.d(TAG, "inside onStart()");*/


        });

        //phnNumber.setText(contact);


    }    /*private void collectPhoneNumbers(Map<String,Object> users) {

        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((Long) singleUser.get("contact"));

        }

        Log.d(TAG,"ArrayList="+ phoneNumbers.toString());
    }*/

    /*@Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        Log.d(TAG, "inside onStop()");
        }*/

}