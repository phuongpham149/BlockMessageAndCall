package com.example.phuong.blockmessageandcall.fragments;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.adapters.ContactAdapter;
import com.example.phuong.blockmessageandcall.models.Contact;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuong on 26/12/2016.
 */
@EFragment(R.layout.fragment_contact_list)
public class ContactListFragment extends BaseFragment {
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<Contact> mContacts;
    private ContactAdapter mContactAdapter;

    @Override
    public void inits() {
        getDataContactFromDevice();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mContactAdapter = new ContactAdapter(getContext(),mContacts);
        mRecyclerView.setAdapter(mContactAdapter);
    }

    public List<Contact> getDataContactFromDevice() {
        mContacts = new ArrayList<>();
        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mContacts.add(new Contact(name, phoneNumber));
        }
        phones.close();
        return mContacts;
    }
}
