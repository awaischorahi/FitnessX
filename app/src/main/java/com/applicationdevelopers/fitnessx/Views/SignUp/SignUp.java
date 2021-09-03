package com.applicationdevelopers.fitnessx.Views.SignUp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.ProfileDetails.CompleteProfile;

import org.bson.Document;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;


public class SignUp extends AppCompatActivity {
    Button register;
    EditText firstname, lastname, email, password;
    CheckBox checkBox;
    User user;
    App app;
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;
    String appID = "fitnessx-rxhnf";
    boolean this_field = false;
    private AtomicReference<User> atomicReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Init();

        //mongodb databse


        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appID).build());

        user = app.currentUser();
        mongodb(user);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    startActivity(new Intent(getApplicationContext(), CompleteProfile.class));
                SignUp();
            }
        });
    }

    public void Init() {
        register = findViewById(R.id.register1);
        firstname = findViewById(R.id.first_name1);
        lastname = findViewById(R.id.last_name1);
        email = findViewById(R.id.email_name1);
        password = findViewById(R.id.password_name1);
        checkBox = findViewById(R.id.checkbox_1);
    }

    public void SignUp() {
        String firstname_get = firstname.getText().toString();
        String lastname_get = lastname.getText().toString();
        String email_get = email.getText().toString();
        String password_get = password.getText().toString();

        if (TextUtils.isEmpty(firstname_get)) {
            firstname.setError("Field is empty");
            return;
        }
        if (TextUtils.isEmpty(lastname_get)) {
            lastname.setError("Field is empty");
            return;
        }
        if (TextUtils.isEmpty(email_get)) {
            lastname.setError("Field is empty");
            return;
        }
        if (!checkBox.isChecked()) {
            Toast.makeText(this, "Kindly accept our privacy", Toast.LENGTH_SHORT).show();
            return;
        } else {
            app.getEmailPassword().registerUserAsync(email_get, password_get, t -> {
                if (t.isSuccess()) {
                    Toast.makeText(this, "Successfully created account", Toast.LENGTH_SHORT).show();

                    mongodb(user);

                    mongoCollection.insertOne(new Document("userid", user.getId()).append("firstname", firstname_get).append("lastname",lastname_get).append("email",email_get).append("password",password_get)).getAsync(new App.Callback<InsertOneResult>() {
                        @Override
                        public void onResult(App.Result<InsertOneResult> result) {
                            Toast.makeText(SignUp.this, "Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), CompleteProfile.class));
                        }
                    });
                } else {
                    Log.wtf("This value ->", t.getError().toString());
                }
            });

        }

    }

    public void mongodb(User user) {
        mongoClient = user.getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("FitnessX");

        mongoCollection = mongoDatabase.getCollection("FitnessXCollection");


    }

}