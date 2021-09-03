package com.applicationdevelopers.fitnessx.Views.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.applicationdevelopers.fitnessx.R;

import org.bson.Document;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class CompleteProfile extends AppCompatActivity {
    EditText dateofbirth, height, weight;
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;
    String appID = "fitnessx-rxhnf";
    User user;

    Button next_complete;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Init();

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appID).build());

        next_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(getApplicationContext(), GoalActivity.class));
            }
        });
    }

    public void Init() {

        next_complete = findViewById(R.id.next_completeprofile);
        dateofbirth = findViewById(R.id.birthday);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);


    }

    public void mongodb(User user) {
        mongoClient = user.getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("FitnessX");

        mongoCollection = mongoDatabase.getCollection("FitnessXCollection");


    }
}