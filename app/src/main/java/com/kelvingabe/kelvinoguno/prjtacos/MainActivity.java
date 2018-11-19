package com.kelvingabe.kelvinoguno.prjtacos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.kelvingabe.kelvinoguno.prjtacos.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, AccountFragment.OnListFragmentInteractionListener, TransactionsFragment.OnListFragmentInteractionListener {
    FloatingActionButton fab;

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
                case R.id.navigation_accounts:
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
            HomeFragment homeFragment = new HomeFragment();

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
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
