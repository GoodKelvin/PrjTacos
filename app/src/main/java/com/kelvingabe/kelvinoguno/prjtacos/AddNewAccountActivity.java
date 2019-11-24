package com.kelvingabe.kelvinoguno.prjtacos;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kelvingabe.kelvinoguno.prjtacos.adapter.NewAccountCountrySpinnerAdapter;
import com.kelvingabe.kelvinoguno.prjtacos.database.AppDatabase;
import com.kelvingabe.kelvinoguno.prjtacos.database.AppExecutors;
import com.kelvingabe.kelvinoguno.prjtacos.database.RecipientAccountEntry;

import java.util.List;

public class AddNewAccountActivity extends AppCompatActivity {
    EditText accountName;
    AutoCompleteTextView bankName;
    EditText accountNum;
    EditText accountNum2;
    Spinner countriesSpinner;
    TextView warningTextView;
    Button saveButton;
    String[] spinnerTitles;
    int[] spinnerImages;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_account);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDb = AppDatabase.getInstance(getApplicationContext());
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getRecipientAccounts().observe(this, new Observer<List<RecipientAccountEntry>>() {
            @Override
            public void onChanged(@Nullable List<RecipientAccountEntry> movieEntries) {
                //gridview.setAdapter(new MainGridviewAdapter(FavoriteMoviesActivity.this, movieEntries));
            }
        });
        spinnerTitles = new String[]{"Nigeria", "More Countries soon"};
        spinnerImages = new int[]{R.drawable.nigeria_flag
                , R.mipmap.ic_launcher};
        initializeUI();
    }


    private void initializeUI() {
        accountName = findViewById(R.id.new_account_name_editText);
        bankName = findViewById(R.id.new_account_bank_textView);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.nigerian_banks_array, android.R.layout.simple_dropdown_item_1line);
        bankName.setAdapter(adapter);
        bankName.setThreshold(0);
        bankName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                bankName.showDropDown();
                validateAccountName();
                return false;
            }
        });
        accountNum = findViewById(R.id.new_account_number_editText);
        accountNum2 = findViewById(R.id.new_account_number2_editText);
        countriesSpinner = findViewById(R.id.new_account_country_spinner);
        warningTextView = findViewById(R.id.new_account_warning_textView);
        saveButton = findViewById(R.id.new_account_save_button);
        NewAccountCountrySpinnerAdapter newAccountCountrySpinnerAdapter = new NewAccountCountrySpinnerAdapter(this, spinnerTitles, spinnerImages);
        countriesSpinner.setAdapter(newAccountCountrySpinnerAdapter);
    }

    private boolean validateAccountName() {
        if (accountName.getText().toString().trim().isEmpty()) {
            accountName.setError(getString(R.string.error_enter_account_name));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAccountNumber() {
        if (accountNum.getText().toString().trim().isEmpty()) {
            accountNum.setError(getString(R.string.error_enter_account_number));
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAccountNumber2() {
        if (!accountNum2.getText().toString().trim().equals(accountNum.getText().toString().trim())) {
            accountNum2.setError(getString(R.string.error_account_number_match));
            return false;
        } else {
            return true;
        }
    }

    public void onSaveAccountClicked(View v) {
        if (validateAccountName() && validateAccountNumber() && validateAccountNumber2()) {
            String name = accountName.getText().toString();
            String number = accountNum.getText().toString();
            String country = getString(R.string.nigeria);
            String bank = bankName.getText().toString();
            final RecipientAccountEntry recipientAccountEntry = new RecipientAccountEntry(name.trim(), name, country, bank, number);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    mDb.recipientAccountDao().insertRecipientAccount(recipientAccountEntry);
                }
            });
            finish();
        } else {

        }
    }
}
