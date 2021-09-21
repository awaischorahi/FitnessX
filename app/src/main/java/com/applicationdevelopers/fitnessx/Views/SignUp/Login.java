package com.applicationdevelopers.fitnessx.Views.SignUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.SharedPreferenceClass.OnboardingSharedPreference;
import com.applicationdevelopers.fitnessx.Views.Home.HomeActivity;
import com.applicationdevelopers.fitnessx.Views.ProfileDetails.CompleteProfile;
import com.applicationdevelopers.fitnessx.Views.ProfileDetails.Welcome;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.bson.Document;

import java.util.HashMap;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class Login extends AppCompatActivity {
    Button btnlogin;
    EditText email, password;
    TextView register_textview;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String userid;
    ProgressDialog progressDialog;
    ImageView google_login;
    GoogleSignInClient mgoogleClient;

    /*
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;
    String appID = "fitnessx-rxhnf";
    User user;
*/

    Button next_complete;
/*
    private App app;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Init();
        /*

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appID).build());
        user = app.currentUser();
        mongodb(user);
*/
    }

    public void Init() {
        btnlogin = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_name_login);
        password = findViewById(R.id.password_name_login);
        google_login = findViewById(R.id.google_login);
        register_textview = findViewById(R.id.register_textview1);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        progressDialog = new ProgressDialog(this);
        GoogleSignInOptions options = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mgoogleClient = GoogleSignIn.getClient(this, options);

        ButtonFunction();


    }

    public void ButtonFunction() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    startActivity(new Intent(getApplicationContext(), Welcome.class));
                Login();
            }
        });
        register_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = mgoogleClient.getSignInIntent();
                startActivityForResult(intent, 99);


            }
        });
    }

    public void Login() {
        String emailtext = email.getText().toString();
        String passwordtext = password.getText().toString();

        if (TextUtils.isEmpty(emailtext)) {
            email.setError("Field is empty");
            return;
        }
        if (TextUtils.isEmpty(passwordtext)) {
            password.setError("Field is empty");
            return;
        } else {
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage("Please wait");
            progressDialog.setTitle("Logging in");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(emailtext, passwordtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    } else {
                        String error = task.getException().toString();
                        String error_split = error.split("n:")[1];
                        Toast.makeText(Login.this, "" + error_split, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });


         /*   Credentials credentials=Credentials.emailPassword(emailtext,passwordtext);
            app.loginAsync(credentials, new App.Callback<User>() {
                @Override
                public void onResult(App.Result<User> result) {
               if (result.isSuccess()){
                   Intent intent=new Intent(getApplicationContext(), CompleteProfile.class);
                   startActivity(intent);
               }
                }
            });
*/
        }
    }


//    public void mongodb(User user) {
//        mongoClient = user.getMongoClient("mongodb-atlas");
//        mongoDatabase = mongoClient.getDatabase("FitnessX");
//
//        mongoCollection = mongoDatabase.getCollection("FitnessXCollection");
//
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            Task<GoogleSignInAccount> taskaccount = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount googleSignInAccount = taskaccount.getResult(ApiException.class);
                setFirebaseAuthgoogle(googleSignInAccount.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }

    }

    public void setFirebaseAuthgoogle(String idtoken) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idtoken, null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                userid=firebaseAuth.getCurrentUser().getUid();
                                if (snapshot.child(userid).hasChildren()) {
                                    databaseReference.child(userid).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                HashMap hashMap = (HashMap) snapshot.getValue();
                                                if (hashMap.get("birthday") == null) {
                                                    startActivity(new Intent(getApplicationContext(), CompleteProfile.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                                } else {
                                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}