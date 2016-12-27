package com.example.phuong.blockmessageandcall.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
@EFragment(R.layout.fragment_message_list)
public class MessageListFragment extends BaseFragment {
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<Contact> mMessage;
    private ContactAdapter mContactAdapter;

    @Override
    public void inits() {
        getDataMessage();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mContactAdapter = new ContactAdapter(getContext(), mMessage);
        mRecyclerView.setAdapter(mContactAdapter);
    }

    public void getDataMessage() {
        mMessage = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c = getActivity().getContentResolver().query(uri, null, null, null, null);
        getActivity().startManagingCursor(c);
        if (c.moveToFirst()) {
            for (int i = 0; i < c.getCount(); i++) {
                Contact sms = new Contact(c.getString(c.getColumnIndexOrThrow("address")).toString(), c.getString(c.getColumnIndexOrThrow("body")).toString());
                mMessage.add(sms);
                c.moveToNext();
            }
        }
        c.close();
        Log.d("tag1", mMessage.size() + "");
    }
}
