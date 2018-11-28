package com.kelvingabe.kelvinoguno.prjtacos.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelvingabe.kelvinoguno.prjtacos.AccountFragment.OnListFragmentInteractionListener;
import com.kelvingabe.kelvinoguno.prjtacos.R;
import com.kelvingabe.kelvinoguno.prjtacos.database.RecipientAccountEntry;
import com.kelvingabe.kelvinoguno.prjtacos.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAccountRecyclerViewAdapter extends RecyclerView.Adapter<MyAccountRecyclerViewAdapter.ViewHolder> {

    private final List<RecipientAccountEntry> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context context;

    public MyAccountRecyclerViewAdapter(List<RecipientAccountEntry> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        String number = mValues.get(position).getAccount_number();
        String last3 = number.replaceAll(".*?(.?.?.?)?$", "$1");
        holder.mAccountNumberView.setText("*******" + last3);
        holder.mAccountNameView.setText(mValues.get(position).getFull_name());
        holder.mAccountBankView.setText(mValues.get(position).getBank());
        // displaying the first letter of From in icon text
        holder.iconText.setText(mValues.get(position).getFull_name().substring(0, 1));
        applyProfilePicture(holder);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    private void applyProfilePicture(ViewHolder holder) {
        holder.imgProfile.setImageResource(R.drawable.bg_circle);
        holder.imgProfile.setColorFilter(holder.getRandomMaterialColor("400"));
        holder.iconText.setVisibility(View.VISIBLE);
        /*if (!TextUtils.isEmpty(message.getPicture())) {
            Glide.with(mContext).load(message.getPicture())
                    .thumbnail(0.5f)
                    .crossFade()
                    .transform(new CircleTransform(mContext))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imgProfile);
            holder.imgProfile.setColorFilter(null);
            holder.iconText.setVisibility(View.GONE);
        } else {
            holder.imgProfile.setImageResource(R.drawable.bg_circle);
            holder.imgProfile.setColorFilter(message.getColor());
            holder.iconText.setVisibility(View.VISIBLE);
        }*/
    }


    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAccountNameView;
        public final TextView mAccountNumberView;
        public final TextView mAccountBankView;
        public final TextView iconText;
        public RecipientAccountEntry mItem;
        ImageView imgProfile;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mAccountBankView = (TextView) view.findViewById(R.id.account_bank_tv);
            mAccountNameView = (TextView) view.findViewById(R.id.account_name_tv);
            mAccountNumberView = (TextView) view.findViewById(R.id.account_number_tv);
            iconText = (TextView) view.findViewById(R.id.icon_text);
            imgProfile = (ImageView) view.findViewById(R.id.icon_profile);
        }

        @Override
        public String toString() {
            //return super.toString() + " '" + mContentView.getText() + "'";
            return " ";
        }

        private int getRandomMaterialColor(String typeColor) {
            int returnColor = Color.GRAY;
            int arrayId = context.getResources().getIdentifier("color_" + typeColor, "array", context.getPackageName());

            if (arrayId != 0) {
                TypedArray colors = context.getResources().obtainTypedArray(arrayId);
                int index = (int) (Math.random() * colors.length());
                returnColor = colors.getColor(index, Color.GRAY);
                colors.recycle();
            }
            return returnColor;
        }
    }

}
