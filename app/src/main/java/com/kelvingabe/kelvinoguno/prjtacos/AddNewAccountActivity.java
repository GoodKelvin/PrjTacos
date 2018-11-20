package com.kelvingabe.kelvinoguno.prjtacos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kelvingabe.kelvinoguno.prjtacos.adapter.NewAccountCountrySpinnerAdapter;

public class AddNewAccountActivity extends AppCompatActivity {
    EditText accountName;
    EditText bankName;
    EditText accountNum;
    EditText accountNum2;
    Spinner countriesSpinner;
    TextView warningTextView;
    Button saveButton;
    String[] spinnerTitles;
    int[] spinnerImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinnerTitles = new String[]{"Nigeria", "More Countries soon"};
        spinnerImages = new int[]{R.drawable.nigeria_flag
                , R.mipmap.ic_launcher};
        initializeUI();
    }

    private void initializeUI() {
        accountName = findViewById(R.id.new_account_name_editText);
        bankName = findViewById(R.id.new_account_name_editText);
        accountNum = findViewById(R.id.new_account_number_editText);
        accountNum2 = findViewById(R.id.new_account_number2_editText);
        countriesSpinner = findViewById(R.id.new_account_country_spinner);
        warningTextView = findViewById(R.id.new_account_warning_textView);
        saveButton = findViewById(R.id.new_account_save_button);
        saveButton.setClickable(false);
        NewAccountCountrySpinnerAdapter newAccountCountrySpinnerAdapter = new NewAccountCountrySpinnerAdapter(this, spinnerTitles, spinnerImages);
        countriesSpinner.setAdapter(newAccountCountrySpinnerAdapter);
    }

    private void validateAccountName() {
        if (accountName.getText().toString().trim().isEmpty()) {
            accountName.setError("Please enter account full name");
        }
    }
}
