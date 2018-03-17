package app.hackit.hackamigo.authentication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.hackit.hackamigo.activities.HomeActivity;
import app.hackit.hackamigo.utils.Constants;

import static android.content.ContentValues.TAG;

/**
 * Created by karan on 3/11/2018.
 */

public class FirebaseAuthentication {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Context mContext;

    public FirebaseAuthentication(Context context) {
        this.mContext = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void startSignUp(final String email, final String password, final String name, final ProgressDialog progressDialog) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(mContext, "SUCCESS",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String UID = currentUser.getUid();

                            // create a database reference for the user
                            mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.KEY_USERS).child(UID);
                            mDatabase.child(Constants.KEY_CHILD_USERS).setValue(email);
                            mDatabase.child(Constants.KEY_CHILD_NAME).setValue(name);
                            //userEmailVerification();
                            progressDialog.dismiss();

                            mContext.startActivity(new Intent(mContext, HomeActivity.class));
                            ((Activity) mContext).finish();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "FAILURE",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }


    public void startSignIn(final String email, final String password, final ProgressDialog progressDialog) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(mContext, "SUCCESS",
                                    Toast.LENGTH_SHORT).show();

                            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.KEY_USERS).child(user_id);
                            DatabaseReference current_user = mDatabase;
                            current_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            progressDialog.dismiss();
                            mContext.startActivity(new Intent(mContext, HomeActivity.class));
                            ((Activity) mContext).finish();


                        } else {

                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "ERROR",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                });
    }
}
