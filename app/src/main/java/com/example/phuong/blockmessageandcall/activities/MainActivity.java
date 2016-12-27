package com.example.phuong.blockmessageandcall.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.adapters.NavAdapter;
import com.example.phuong.blockmessageandcall.fragments.BlockContactFragment;
import com.example.phuong.blockmessageandcall.fragments.BlockContactFragment_;
import com.example.phuong.blockmessageandcall.fragments.ContactListFragment;
import com.example.phuong.blockmessageandcall.fragments.ContactListFragment_;
import com.example.phuong.blockmessageandcall.fragments.EventCalendarFragment;
import com.example.phuong.blockmessageandcall.fragments.EventCalendarFragment_;
import com.example.phuong.blockmessageandcall.fragments.MessageListFragment;
import com.example.phuong.blockmessageandcall.fragments.MessageListFragment_;
import com.example.phuong.blockmessageandcall.listeners.OnNavListener;
import com.example.phuong.blockmessageandcall.models.ItemNav;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements OnNavListener {
    @ViewById(R.id.frContainer)
    FrameLayout mFrameLayout;
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<ItemNav> mItemNavs;
    private NavAdapter mNavAdapter;

    @Override
    void inits() {
        initDataRecyclerView();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        mNavAdapter = new NavAdapter(this, mItemNavs, this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mNavAdapter);
        displayView(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    public void initDataRecyclerView() {
        mItemNavs = new ArrayList<>();
        mItemNavs.add(new ItemNav(1, "Contact"));
        mItemNavs.add(new ItemNav(2, "Message"));
        mItemNavs.add(new ItemNav(1, "Block Call"));
        mItemNavs.add(new ItemNav(1, "Event Calendar"));
    }

    @Override
    public void onItemClickListener(int position) {
        displayView(position);
    }

    public void displayView(int position) {
        switch (position) {
            case 0:
                ContactListFragment mContactListFragment = ContactListFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, mContactListFragment).commit();
                break;
            case 1:
                MessageListFragment mMessageListFragment = MessageListFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, mMessageListFragment).commit();
                break;
            case 2:
                BlockContactFragment mBlockContactFragment = BlockContactFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, mBlockContactFragment).commit();
                break;
            case 3:
                EventCalendarFragment mBlockMessageFragment = EventCalendarFragment_.builder().build();
                getSupportFragmentManager().beginTransaction().replace(R.id.frContainer, mBlockMessageFragment).commit();
                break;
            default:
                break;
        }
    }
}
