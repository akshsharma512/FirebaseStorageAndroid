package com.example.akshatsharma.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main5Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String uid, name;
    private final static String TAG = "Main5Activity";
    List<String> lst, nameList, newList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mAuth = FirebaseAuth.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userData");

        //database = FirebaseDatabase.getInstance();
        lst = new ArrayList<String>();
        nameList = new ArrayList<String>();
        newList = new ArrayList<String>();

        Log.d(TAG, "Ref is " + myRef);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                /*for(DataSnapshot dsp : dataSnapshot){
                    lst.add(String.valueOf(dsp.getValue())); //add result into array list
                }*/
                //ArrayList arrrayList  = new ArrayList();
                //Details details = dataSnapshot.getValue(Details.class);

                //Log.d(TAG, "details name= "+ details.getName().toString());

                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    lst.add(String.valueOf(dsp.getValue())); //add result into array list
                }

                //lst.add(String.valueOf(dataSnapshot.getValue().toString()));
                Log.d(TAG, "List is="+lst);
                Log.d(TAG, "List Size="+lst.size());

                try {
                    JSONArray jsonArray = new JSONArray(lst);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.getJSONArray(jsonArray.toString());

                for(int i=0;i<lst.size();i++){
                    nameList.add(lst.get(i));
                    Log.d(TAG, "nameList is=" +nameList);
                    for(int j=0;j<nameList.size();j++){
                        //HashMap<String, String> hashmap= nameList.get(i);
                        //String string= hashmap.get("Your_Key_Name");
                            //newList.add(jsonObject.get("name").toString());

                        /*if(nameList.get(j).contains("name"))
                        {
                            newList.add(nameList.get(j));
                        }*/
                    }
                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "newList is = "+newList);
                /*for(List<String> nameList : lst){

                }
*/
                /*for(int i = 0; i<= lst.size();i++)
                {
                    for(int j=0; j<=i;j++)
                    {
                        arrrayList.add(lst.get(j));
                    }
                }
                Log.i(TAG,"Array List ="+ arrrayList.toString());*/


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
    }
}
