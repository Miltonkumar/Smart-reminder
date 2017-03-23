package in.ac.utm.smartreminder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "FB_SIGNIN";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText pass;
    private EditText email;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Set up click handlers and view item references
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.log_out).setOnClickListener(this);
        findViewById(R.id.log_goog).setOnClickListener(this);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        email = (EditText)findViewById(R.id.email_id);
        pass = (EditText)findViewById(R.id.passwd);

        // TODO: Get a reference to the Firebase auth object
        mAuth = FirebaseAuth.getInstance();

        // TODO: Attach a new AuthListener to detect sign in and out
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
    /**
     * When the Activity starts and stops, the app needs to connect and
     * disconnect the AuthListener
     */
    @Override
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                signUserIn();
                break;
            case R.id.log_goog:
                Intent intent=new Intent(login.this,Reset_password.class);
                startActivity(intent);

            case R.id.log_out:
                signUserOut();
                break;
        }
    }

    protected boolean checkFormFields() {
        String mail, password;
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
        TextView tvStat = (TextView)findViewById(R.id.log_in_status);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            tvStat.setText("Signed in: " + user.getEmail());

        }
        else {
            tvStat.setText("Signed Out");
        }
    }

    protected void updateStatus(String stat) {
        TextView tvStat = (TextView)findViewById(R.id.log_in_status);
        tvStat.setText(stat);
    }

    protected void signUserIn() {
        if (!checkFormFields())
            return;
        spinner.setVisibility(View.VISIBLE);
        String myemail = email.getText().toString();
        String password = pass.getText().toString();

        // TODO: sign the user in with email and password credentials
        mAuth.signInWithEmailAndPassword(myemail,password)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    spinner.setVisibility(View.GONE);
                                    Toast.makeText(login.this, "Signed in", Toast.LENGTH_SHORT)
                                            .show();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                         // Name, email address, and profile photo Url
                                        String name = user.getDisplayName();
                                         String email = user.getEmail();
                                         Uri photoUrl = user.getPhotoUrl();
                                        UserDetails.getUserName(email);

                                        // Check if user's email is verified
                                         boolean emailVerified = user.isEmailVerified();

                                         // The user's ID, unique to the Firebase project. Do NOT use this value to
                                         // authenticate with your backend server, if you have one. Use
                                         // FirebaseUser.getToken() instead.
                                         String uid = user.getUid();
                                        Intent intent=new Intent(login.this,Users.class);
                                        startActivity(intent);
                                    }
                                }
                                else {
                                    spinner.setVisibility(View.GONE);
                                    Toast.makeText(login.this, "Sign in failed", Toast.LENGTH_SHORT)
                                            .show();
                                }

                                updateStatus();
                            }
                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            spinner.setVisibility(View.GONE);
                            updateStatus("Invalid password.");
                        }
                        else if (e instanceof FirebaseAuthInvalidUserException) {
                            spinner.setVisibility(View.GONE);
                            updateStatus("No account with this email.");
                        }
                        else {
                            spinner.setVisibility(View.GONE);
                            updateStatus(e.getLocalizedMessage());
                        }
                    }
                });
    }
    private void signUserOut() {
        // TODO: sign the user out
        mAuth.signOut();
        spinner.setVisibility(View.GONE);
        updateStatus();
    }
}
