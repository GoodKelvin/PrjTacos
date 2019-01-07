package com.kelvingabe.kelvinoguno.prjtacos;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.kelvingabe.kelvinoguno.prjtacos.adapter.AccountsAdapter;
import com.kelvingabe.kelvinoguno.prjtacos.database.AppDatabase;
import com.kelvingabe.kelvinoguno.prjtacos.database.RecipientAccountEntry;
import com.kelvingabe.kelvinoguno.prjtacos.util.CurrencyConverter;
import com.kelvingabe.kelvinoguno.prjtacos.util.RecipientAccountInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Unbinder unbinder;
    @BindView(R.id.to_editText)
    AutoCompleteTextView recipient_name_input;

    @BindView(R.id.send_editText)
    EditText send_editText;

    @BindView(R.id.receive_editText)
    EditText receive_editText;

    AppDatabase mDb;
    MainViewModel mainViewModel;
    private ArrayList<String> accNames;
    private ArrayList<String> accNumbers;
    private ArrayList<String> accBanks;
    CurrencyConverter currencyConverter = new CurrencyConverter();


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureRecipientInput();
        convertInputs();
    }

    protected void configureRecipientInput() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getRecipientAccounts().observe(this, new Observer<List<RecipientAccountEntry>>() {
            @Override
            public void onChanged(@Nullable List<RecipientAccountEntry> movieEntries) {
                AccountsAdapter adapter = new AccountsAdapter(getActivity(), R.layout.accounts_list_item, makeInfo(movieEntries));
                adapter.setBank(accBanks);
                adapter.setAccNumber(accNumbers);
                recipient_name_input.setAdapter(adapter);
            }
        });

        recipient_name_input.setThreshold(0);
        recipient_name_input.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                recipient_name_input.showDropDown();
                //validateAccountName();
                return false;
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private List<String> makeInfo(List<RecipientAccountEntry> movieEntries) {
        accNames = new ArrayList<String>();
        accNumbers = new ArrayList<String>();
        accBanks = new ArrayList<String>();
        for (int i = 0; i < movieEntries.size(); i++) {
            accNames.add(i, movieEntries.get(i).getFull_name());
            accNumbers.add(i, movieEntries.get(i).getAccount_number());
            accBanks.add(i, movieEntries.get(i).getBank());
        }
        RecipientAccountInfo recipientAccountInfo = new RecipientAccountInfo();
        recipientAccountInfo.setAccBank(accBanks);
        return accNames;
    }

    private void convertInputs() {
        send_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //receive_editText.setText("blah");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                double d = currencyConverter.usdToNaira(Double.parseDouble(s), 0);
                receive_editText.setText(String.valueOf(d));
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
