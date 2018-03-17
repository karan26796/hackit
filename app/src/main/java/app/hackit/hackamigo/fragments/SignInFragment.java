package app.hackit.hackamigo.fragments;

import android.app.ProgressDialog;
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
import app.hackit.hackamigo.authentication.FirebaseAuthentication;

/**
 * Created by karan on 3/11/2018.
 */

public class SignInFragment extends Fragment implements View.OnClickListener {
    Button btnSignIn;
    EditText mEmailField, mPassField;
    String email, password;
    ProgressDialog mProgressDialog;
    FirebaseAuth mAuth;

    public static SignInFragment newInstance() {

        Bundle args = new Bundle();

        SignInFragment fragment = new SignInFragment();
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
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_sign_in,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignIn = view.findViewById(R.id.button_sign_in);

        mEmailField = view.findViewById(R.id.edit_text_sign_in_email);
        mPassField = view.findViewById(R.id.edit_text_sign_in_password);

        btnSignIn.setOnClickListener(this);
    }

    private void startSignIn() {

        mProgressDialog.setTitle("WAIT");
        mProgressDialog.setMessage("Logging In");
        mProgressDialog.setCanceledOnTouchOutside(false);

        email = mEmailField.getText().toString().trim();
        password = mPassField.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(email)) {
                mEmailField.setError("ERROR");

            }
            if (TextUtils.isEmpty(password)) {
                mPassField.setError("ERROR");
            }

        } else {
            mProgressDialog.show();

            FirebaseAuthentication firebaseAuthentication = new FirebaseAuthentication(getContext());
            firebaseAuthentication.startSignIn(email, password, mProgressDialog);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                startSignIn();
                break;
        }

    }
}
