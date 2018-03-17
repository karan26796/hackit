package app.hackit.hackamigo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.hackit.hackamigo.R;
import app.hackit.hackamigo.fragments.SignInFragment;
import app.hackit.hackamigo.fragments.SignUpFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    Button btnSignUp;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpToolbar(this);

        btnSignUp = findViewById(R.id.button_login_sign_up);

        if (savedInstanceState == null)
            fragmentInflate(SignInFragment.newInstance());

        btnSignUp.setOnClickListener(this);
    }

    public void fragmentInflate(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.login_page_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected int getToolbarID() {
        return R.id.login_activity_toolbar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_sign_up:
                fragmentInflate(SignUpFragment.newInstance());
                break;
        }
    }
}
