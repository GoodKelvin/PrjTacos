package com.kelvingabe.kelvinoguno.prjtacos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelvingabe.kelvinoguno.prjtacos.controller.AddNewAccountActivity;
import com.kelvingabe.kelvinoguno.prjtacos.controller.BankCardActivity;
import com.kelvingabe.kelvinoguno.prjtacos.controller.PersonalInfoActivity;
import com.kelvingabe.kelvinoguno.prjtacos.controller.ReviewTransactionActivity;
import com.kelvingabe.kelvinoguno.prjtacos.database.AppDatabase;
import com.kelvingabe.kelvinoguno.prjtacos.database.RecipientAccountEntry;
import com.kelvingabe.kelvinoguno.prjtacos.dummy.DummyContent;
import com.kelvingabe.kelvinoguno.prjtacos.model.MainViewModel;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, AccountFragment.OnListFragmentInteractionListener, TransactionsFragment.OnListFragmentInteractionListener {
    FloatingActionButton fab;
    MainViewModel mainViewModel;
    AppDatabase mDb;
    HomeFragment homeFragment;
    private Toolbar mToolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    addHomeFragment();
                    return true;
                case R.id.navigation_activity:
                    addTransactionFragment();
                    return true;
                case R.id.navigation_recipients:
                    addAccountFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(getApplicationContext(), AddNewAccountActivity.class));
            }
        });
        addHomeFragment();
    }

    @SuppressLint("RestrictedApi")
    private void addHomeFragment() {
        fab.setVisibility(View.INVISIBLE);
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (getIntent().getExtras() != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            homeFragment = new HomeFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            homeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment).commit();
        }
    }

    @SuppressLint("RestrictedApi")
    private void addAccountFragment() {
        fab.setVisibility(View.VISIBLE);
        if (findViewById(R.id.fragment_container) != null) {
            if (getIntent().getExtras() != null) {
                return;
            }
            AccountFragment accountFragment = new AccountFragment();
            accountFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, accountFragment).commit();

        }
    }

    @SuppressLint("RestrictedApi")
    private void addTransactionFragment() {
        fab.setVisibility(View.INVISIBLE);
        if (findViewById(R.id.fragment_container) != null) {
            if (getIntent().getExtras() != null) {
                return;
            }
            TransactionsFragment transactionsFragment = new TransactionsFragment();
            transactionsFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, transactionsFragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(RecipientAccountEntry item) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void onSendClicked(View view) {
        if (homeFragment.onSendClicked(view)) {
            homeFragment.marshalData();
            startActivity(new Intent(getApplicationContext(), ReviewTransactionActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            case R.id.action_personal_info:
                showPersonalInfo();
                return true;
            case R.id.action_bank_cards:
                showBankCards();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // user is now signed out
                        Intent intent = new Intent(getApplicationContext(), LaunchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void showBankCards() {
        startActivity(new Intent(getApplicationContext(), BankCardActivity.class));
    }

    private void showPersonalInfo() {
        startActivity(new Intent(getApplicationContext(), PersonalInfoActivity.class));
    }
}
