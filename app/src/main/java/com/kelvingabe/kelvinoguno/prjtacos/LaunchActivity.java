package com.kelvingabe.kelvinoguno.prjtacos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelvingabe.kelvinoguno.prjtacos.model.App;

import java.util.Arrays;

public class LaunchActivity extends AppCompatActivity {
    public static final int RC_SIGN_IN = 1;
    Boolean mSignInStatus = false;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mFirebaseAuth = FirebaseAuth.getInstance();
        //goToHome();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepSignIn();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void prepSignIn() {
        mAuthStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                App.prefs.setUserEmail(user.getEmail());
                App.prefs.setUserID(user.getUid());
                onSignedInInitialize(user.getDisplayName());
                //miscUtil.firestoreSaveUser(id, email);
                onSignedIn();
            } else {
                // User is signed out
                onSignedOutCleanup();
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                //startLogin();
            }
        };
    }

    private void startLogin() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build()))
                        .build(),
                RC_SIGN_IN);
    }

    public void onSignInClicked(View view) {
        startLogin();
    }

    private void onSignedInInitialize(String userId) {
        mUserId = userId;
        mSignInStatus = true;
    }

    private void onSignedOutCleanup() {
        //mUserId = ANONYMOUS;
        //mMessageAdapter.clear();
        //detachDatabaseReadListener();
    }

    public void onSignedIn() {
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
        goToHome();
    }

    private void goToHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
