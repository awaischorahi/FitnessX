package com.applicationdevelopers.fitnessx.Views.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.applicationdevelopers.fitnessx.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;
import io.realm.mongodb.mongo.result.UpdateResult;

public class CompleteProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText dateofbirth, height, weight;
/*
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;
    String appID = "fitnessx-rxhnf";
    User user;
*/

    Button next_complete;
    private App app;
    Spinner spinnergender;
    private String selected_gender = "male";
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Init();
/*

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appID).build());
        user = app.currentUser();
*/

        next_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileCompleteInfo();
                //startActivity(new Intent(getApplicationContext(), GoalActivity.class));
            }
        });
    }

    public void Init() {

        next_complete = findViewById(R.id.next_completeprofile);
        dateofbirth = findViewById(R.id.birthday);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        spinnergender = findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergender.setAdapter(adapter);
        spinnergender.setSelection(0);

        spinnergender.setOnItemSelectedListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userid = firebaseAuth.getCurrentUser().getUid();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                selected_gender = "male";
                return;
            case 2:
                selected_gender = "female";
                return;
            default:
                return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void profileCompleteInfo() {
        String birthday = dateofbirth.getText().toString();
        String height_ = height.getText().toString();
        String weight_ = weight.getText().toString();

        if (TextUtils.isEmpty(birthday)) {
            dateofbirth.setError("Field is empty");
            return;
        }
        if (TextUtils.isEmpty(height_)) {
            height.setError("Field is empty");
            return;
        }
        if (TextUtils.isEmpty(weight_)) {
            weight.setError("Field is empty");
            return;
        } else {

            HashMap<String, Object> values = new HashMap<>();
            values.put("birthday", birthday);
            values.put("weight", weight_);
            values.put("height", height_);
            values.put("gender", selected_gender);

            databaseReference.child(userid).updateChildren(values);
            Intent intent = new Intent(this, GoalActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


            // mongodb(user);
            /*Document document = new Document("userid", user.getId());
//            Document query = new Document("userid", user.getId());
            Document update = new Document("$set", new Document("userid", user.getId()).append("weight", weight_).append("height", height_));

            mongoCollection.updateOne(document, update).getAsync(new App.Callback<UpdateResult>() {
                @Override
                public void onResult(App.Result<UpdateResult> result) {

                }
            });*/
        }
    }



    /*
    public void mongodb(User user) {
        mongoClient = user.getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("FitnessX");

        mongoCollection = mongoDatabase.getCollection("FitnessXCollection");


    }*/
}