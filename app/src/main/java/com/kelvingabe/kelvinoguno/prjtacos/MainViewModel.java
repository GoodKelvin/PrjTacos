package com.kelvingabe.kelvinoguno.prjtacos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kelvingabe.kelvinoguno.prjtacos.database.AppDatabase;
import com.kelvingabe.kelvinoguno.prjtacos.database.RecipientAccountEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<RecipientAccountEntry>> recipientAccounts;
    AppDatabase appDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(getApplication());
        recipientAccounts = appDatabase.recipientAccountDao().loadAllRecipientAccounts();
    }

    public LiveData<List<RecipientAccountEntry>> getRecipientAccounts() {
        if (recipientAccounts == null) {
            appDatabase.recipientAccountDao().loadAllRecipientAccounts();
        }
        return recipientAccounts;
    }
}
