package com.kelvingabe.kelvinoguno.prjtacos.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.kelvingabe.kelvinoguno.prjtacos.R;
import com.kelvingabe.kelvinoguno.prjtacos.model.RecipientAccountInfo;

import java.util.ArrayList;
import java.util.List;

public class AccountsAdapter extends ArrayAdapter {

    private List<String> dataList;
    private Context mContext;
    private int itemLayout;

    private ListFilter listFilter = new ListFilter();
    private List<String> dataListAllItems;
    private List<String> banks;
    private List<String> accNums;
    private List<String> hybridInfo;


    public AccountsAdapter(Context context, int resource, List<String> storeDataLst) {
        super(context, resource, storeDataLst);
        dataList = storeDataLst;
        mContext = context;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String getItem(int position) {
        Log.e("CustomListAdapter",
                dataList.get(position));
        return hybridInfo.get(position);
    }

    public void setBank(List<String> s) {
        this.banks = s;
    }

    public void setAccNumber(List<String> s) {
        this.accNums = s;
    }

    public void setHybridInfo(List<String> s) {
        this.hybridInfo = s;
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }
        RecipientAccountInfo recipientAccountInfo = new RecipientAccountInfo();
        TextView strName = (TextView) view.findViewById(R.id.recipient_name_textView);
        strName.setText(dataList.get(position));
        TextView strName1 = (TextView) view.findViewById(R.id.recipient_bank_textView);
        strName1.setText(banks.get(position));

        TextView strName2 = (TextView) view.findViewById(R.id.recipient_acc_num_textView);
        String number = accNums.get(position);
        String last3 = number.replaceAll(".*?(.?.?.?)?$", "$1");
        strName2.setText("*****" + last3);

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<String>(dataList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<String> matchValues = new ArrayList<String>();

                for (String dataItem : dataListAllItems) {
                    if (dataItem.toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<String>) results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}