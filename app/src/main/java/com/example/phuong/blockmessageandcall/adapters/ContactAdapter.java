package com.example.phuong.blockmessageandcall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.models.Contact;

import java.util.List;

/**
 * Created by phuong on 21/12/2016.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyHolder> {
    private Context mContext;
    private List<Contact> mContacts;

    public ContactAdapter(Context mContext, List<Contact> mContacts) {
        this.mContext = mContext;
        this.mContacts = mContacts;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_contact, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.mTvPhone.setText(contact.getPhone());
        holder.mTvName.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvPhone;

        public MyHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
        }
    }
}
