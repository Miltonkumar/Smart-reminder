package in.ac.utm.smartreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;


public class register extends AppCompatActivity implements View.OnClickListener{

    protected final String TAG = "FB_SIGNIN";

    protected FirebaseAuth mAuth;
    protected FirebaseAuth.AuthStateListener mAuthListener;

    private EditText pass;
    private EditText email;
   // private EditText username;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up click handlers and view item references
        findViewById(R.id.signUp).setOnClickListener(this);
        findViewById(R.id.sign_google).setOnClickListener(this);

        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        // TODO: Get a reference to the Firebase auth object
        mAuth = FirebaseAuth.getInstance();


        email = (EditText)findViewById(R.id.reg_mail);
        pass = (EditText)findViewById(R.id.reg_pass);
       // username=(EditText)findViewById(R.id.user_name);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "Signed in: " + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "Currently signed out");
                }
            }
        };
        updateStatus();
    }

    public void onStart() {
        super.onStart();
        // TODO: add the AuthListener
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO: Remove the AuthListener
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_google:
                break;

            case R.id.signUp:
                createUserAccount();
                break;
        }
    }

    protected boolean checkFormFields() {
        String mail, password,usern;
        mail = email.getText().toString();
        password = pass.getText().toString();

        if (mail.isEmpty()) {
            email.setError("Email Required");
            return false;
        }
        if (password.isEmpty()){
            pass.setError("Password Required");
            return false;
        }

        return true;
    }

    protected void updateStatus() {
        TextView tvStat = (TextView)findViewById(R.id.reg_status);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            tvStat.setText("Signed in: " + user.getEmail());
        }
        else {
            tvStat.setText("Not Registered");
        }
    }

    protected void updateStatus(String stat) {
        TextView tvStat = (TextView)findViewById(R.id.reg_status);
        tvStat.setText(stat);
    }

     void createUserAccount() {
        if (!checkFormFields())
            return;

         spinner.setVisibility(View.VISIBLE);
        String myemail = email.getText().toString();
        String password = pass.getText().toString();

        // TODO: Create the user account
        mAuth.createUserWithEmailAndPassword(myemail, password)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    spinner.setVisibility(View.GONE);
                                    Toast.makeText(register.this, "User created ", Toast.LENGTH_SHORT)
                                            .show();

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    spinner.setVisibility(View.VISIBLE);
                                    user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                                         @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                spinner.setVisibility(View.GONE);
                                                Log.d(TAG, "Email sent.");
                                                 }
                                             }
                                         });


                                } else {
                                    spinner.setVisibility(View.GONE);
                                    Toast.makeText(register.this, "Account creation failed", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.toString());
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            updateStatus("This email address is already in use.");
                        }
                        else {
                            updateStatus(e.getLocalizedMessage());
                        }
                    }
                });
    }
}
