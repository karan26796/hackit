package app.hackit.hackamigo.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import app.hackit.hackamigo.R;
import app.hackit.hackamigo.activities.HomeActivity;
import app.hackit.hackamigo.authentication.FirebaseAuthentication;

/**
 * Created by karan on 3/11/2018.
 */

public class SignUpFragment extends Fragment implements View.OnClickListener {
    Button btnSignUp;
    EditText mEmailField, mPassField, mNameField;
    String email, password, name;
    ProgressDialog mProgressDialog;
    FirebaseAuth mAuth;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_sign_up, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignUp = view.findViewById(R.id.button_sign_up);

        mNameField = view.findViewById(R.id.edit_text_username);
        mEmailField = view.findViewById(R.id.edit_text_email);
        mPassField = view.findViewById(R.id.edit_text_password);

        btnSignUp.setOnClickListener(this);
    }

    private void signUp() {

        mProgressDialog.setTitle("WAIT");
        mProgressDialog.setMessage("SIGNING IN");
        mProgressDialog.setCanceledOnTouchOutside(false);

        email = mEmailField.getText().toString().trim();
        password = mPassField.getText().toString().trim();
        name = mNameField.getText().toString().trim();


        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(password)||TextUtils.isEmpty(email)) {
            mNameField.setError("ERROR");
        } else {
            mProgressDialog.show();
            // register user to firebase authentication
            // store name and email to firebase database
            FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(getContext());
            firebaseAuthentication.startSignUp(email, password, name, mProgressDialog);
            startActivity(new Intent(getContext(), HomeActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_up:
                signUp();
                break;
        }

    }
}
