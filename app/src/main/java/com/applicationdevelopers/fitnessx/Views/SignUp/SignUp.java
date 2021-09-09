package com.applicationdevelopers.fitnessx.Views.SignUp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.ProfileDetails.CompleteProfile;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.bson.Document;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;
import io.realm.mongodb.sync.SyncConfiguration;


public class SignUp extends AppCompatActivity {
    Button register;
    EditText firstname, lastname, email;
    CheckBox checkBox;
    TextInputEditText password;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    ImageView google, facebook;
    GoogleSignInClient mGoogleSignInClient;
    public static final int result = 99;
    TextView login_textView;

    boolean this_field = false;
    private final static Pattern Password = Pattern.compile("^" +
            "(?=.*[0-9])" + // for atleast one number
            "(?=.*[a-z])" + //for atleast one small Alphabets
            "(?=.*[A-Z])" + //for atleast one capital alphabets
            "(?=.*[@#$%^&+=])" + // for atleast one special characters
            "(?=\\S+$)" + "" + // no white spaces
            ".{4,}$"); // minimum strength 4`

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Init();
        ButtonMethods();
        //mongodb databse


/*
        Realm.init(this);
        app = new App(new,, AppConfiguration.Builder(appID).build());

        user = app.currentUser();
            mongodb(user);
*/

    }

    public void Init() {
        register = findViewById(R.id.register1);
        firstname = findViewById(R.id.first_name1);
        lastname = findViewById(R.id.last_name1);
        email = findViewById(R.id.email_name1);
        password = findViewById(R.id.text_input_edittext);
        checkBox = findViewById(R.id.checkbox_1);
        google = findViewById(R.id.google_signin);
        login_textView = findViewById(R.id.login_textview1);
        facebook = findViewById(R.id.facebook_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, options);

    }

    public void ButtonMethods() {


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    startActivity(new Intent(getApplicationContext(), CompleteProfile.class));
                SignUp();
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, result);
            }
        });

        login_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
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
/*
            app.getEmailPassword().registerUserAsync(email_get, password_get, t -> {
                if (t.isSuccess()) {
                    Toast.makeText(this, "Successfully created account", Toast.LENGTH_SHORT).show();
                    mongodb(user);
                    mongoCollection.insertOne(new Document("userid", user.getId()).append("firstname", firstname_get).append("lastname", lastname_get).append("email", email_get).append("password", password_get)).getAsync(new App.Callback<InsertOneResult>() {
                        @Override
                        public void onResult(App.Result<InsertOneResult> result) {
                            if (result.isSuccess()){
                                Toast.makeText(SignUp.this, "Inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), CompleteProfile.class));

                            }else{
                                Log.wtf("inserted data ->",result.getError().toString());
                            }
                        }
                    });
                } else {
                    Log.wtf("This value ->", t.getError().toString());
                }
            });

*/
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setTitle("Logging in");
            progressDialog.setMessage("Please wait");
            progressDialog.show();

            if (Password.matcher(password.getText().toString().trim()).matches()) {

                firebaseAuth.createUserWithEmailAndPassword(email_get, password_get).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userid = firebaseAuth.getCurrentUser().getUid();
                            HashMap<String, Object> values = new HashMap<>();
                            values.put("firstname", firstname_get);
                            values.put("lastname", lastname_get);
                            values.put("email", email_get);
                            values.put("password", password_get);

                            values.put("birthday", "");
                            values.put("weight", "");
                            values.put("height", "");
                            values.put("gender", "");

                            databaseReference.child("Users").child(userid).updateChildren(values);
                            progressDialog.dismiss();

                            Intent intent = new Intent(getApplicationContext(), CompleteProfile.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.wtf("Login error ->", task.getException().toString());
                            String error = task.getException().toString();
                            String spliterror = error.split("n:")[1];
                            Toast.makeText(SignUp.this, "" + spliterror, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

            } else {
                Toast.makeText(this, "enter strong password", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == result) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaeAuth(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }

    public void firebaeAuth(String account) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(account, null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userid = firebaseAuth.getCurrentUser().getUid();
                    HashMap<String, Object> values = new HashMap<>();

                    String fullname = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                    String first_name = fullname.split(" ")[0];
                    String last_name = fullname.split(" ")[1];
                    values.put("firstname", first_name);
                    values.put("lastname", last_name);
                    values.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());

                    values.put("password", "");
                    values.put("birthday", "");
                    values.put("weight", "");
                    values.put("height", "");
                    values.put("gender", "");

                    databaseReference.child("Users").child(userid).updateChildren(values);

                    progressDialog.dismiss();

                    Intent intent = new Intent(getApplicationContext(), CompleteProfile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Log.wtf("Login error ->", task.getException().toString());
                    String error = task.getException().toString();
                    String spliterror = error.split("n:")[1];
                    Toast.makeText(SignUp.this, "" + spliterror, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }
/*
    public void mongodb(User user) {
        mongoClient = user.getMongoClient("mongodb-atlas");
        mongoDatabase = mongoClient.getDatabase("FitnessX");
        mongoCollection = mongoDatabase.getCollection("FitnessXCollection");
    }
*/

}