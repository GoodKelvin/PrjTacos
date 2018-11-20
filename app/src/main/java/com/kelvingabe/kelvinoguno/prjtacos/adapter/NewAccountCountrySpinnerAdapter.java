package com.kelvingabe.kelvinoguno.prjtacos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelvingabe.kelvinoguno.prjtacos.R;

public class NewAccountCountrySpinnerAdapter extends ArrayAdapter {
    String[] spinnerTitles;
    int[] spinnerImages;
    Context mContext;

    public NewAccountCountrySpinnerAdapter(@NonNull Context context, String[] titles, int[] images) {
        super(context, R.layout.new_account_country_spinner_row);
        this.spinnerTitles = titles;
        this.spinnerImages = images;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return spinnerTitles.length;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.new_account_country_spinner_row, parent, false);
            mViewHolder.mFlag = convertView.findViewById(R.id.ivFlag);
            mViewHolder.mName = convertView.findViewById(R.id.tvName);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(spinnerImages[position]);
        mViewHolder.mName.setText(spinnerTitles[position]);

        return convertView;
    }

    private static class ViewHolder {
        ImageView mFlag;
        TextView mName;
    }
}
