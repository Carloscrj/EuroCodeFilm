package uem.dam.eurocodefilms;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail;
    EditText etPwd;
    Button btnAcceder;
    Button btnRegistrar;
    SignInButton btnGoogle;
    private FirebaseAuth fba;
    private FirebaseUser user;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 123;

    public static final String REGEX_PWD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[?¿!#%$])[a-zA-Z\\d?¿!#%$]{8,16}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)" +
            "*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etPassword);
        btnAcceder = findViewById(R.id.btnAcceder);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnGoogle = findViewById(R.id.btnGoogle);

        btnAcceder.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);

        fba = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        user = fba.getCurrentUser();
        if (user == null) {
            btnRegistrar.setEnabled(true);
        } else {
            btnAcceder.setEnabled(true);
            btnRegistrar.setEnabled(false);
            etEmail.setText(user.getEmail());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAcceder) acceder();
        else if (v.getId() == R.id.btnRegistrar) registrar();
        else if (v.getId() == R.id.btnGoogle) accederGoogle();
    }

    private void accederGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void acceder() {
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        if (email.isEmpty() || pwd.isEmpty()) {
            Toast.makeText(this, R.string.no_datos, Toast.LENGTH_LONG).show();
        } else {
            fba.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user = fba.getCurrentUser();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.pwd_no_ok, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void registrar() {
        String email = etEmail.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();

        if (email.isEmpty() || pwd.isEmpty()) {
            Toast.makeText(this, R.string.no_datos, Toast.LENGTH_LONG).show();
        } else {
            boolean validEmail = isValidEmail(email);
            if (validEmail) {
                boolean validPwd = isValidPwd(pwd);
                if (validPwd) {
                    fba.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, R.string.registro_ok, Toast.LENGTH_SHORT).show();
                                btnRegistrar.setEnabled(false);
                                btnAcceder.setEnabled(true);
                                etEmail.setText(email);
                            } else {
                                Toast.makeText(LoginActivity.this, R.string.registro_no_ok, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, R.string.pwd_no_valido, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, R.string.email_no_valido, Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isValidPwd(String pwd) {
        Pattern pattern = Pattern.compile(LoginActivity.REGEX_PWD);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(LoginActivity.REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}