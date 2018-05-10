package com.example.amiramaulina.retrievedataheartbeat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RetrieveApp extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;


    //ArrayAdapter adapter;
    ArrayList<String> array;

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_app);


        DatabaseReference userdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        DatabaseReference ref = userdatabase.child(user.getUid());

        array = new ArrayList<>();
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*for (DataSnapshot childSnapshot: dataSnapshot.child("hrvalue").child("nilaihr").getChildren()) {
                    Log.i("nilaihr array", "array " + (childSnapshot.getValue(Array.class)));
                    //System.out.println(childSnapshot.getValue(String.class));
                }*/

                showData(dataSnapshot);


                /*Integer ageValue = dataSnapshot.getValue(Integer.class);

                Log.i("user age mainactivity", "user age " + ageValue);*/


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {

        //Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
        String nameval = dataSnapshot.child("name").getValue(String.class); //set the name
        String emailval = dataSnapshot.child("email").getValue(String.class); //set the email
        Log.i("database value", "name " + nameval);
        Log.i("database value", "email " + emailval);



        /*array = dataSnapshot.child("hrvalue").child("nilaihr").getValue(CreateHR.class);
        Log.i("database value", "name " + nameval);*/
        //uInfo.setPhone_num(ds.child(userID).getValue(CreateUser.class).getPhone_num()); //set the phone_num





        array.add(nameval);
        array.add(emailval);

        Log.i("database array", "array database " + array);
        // array.add(uInfo.getPhone_num());

        //mListView.setAdapter(adapter);


    }
}
