package com.example.phuong.blockmessageandcall.fragments;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.adapters.BlockAdapter;
import com.example.phuong.blockmessageandcall.models.PhoneBlock;
import com.orm.SugarContext;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuong on 26/12/2016.
 */
@EFragment(R.layout.fragment_block_call_list)
public class BlockContactFragment extends BaseFragment {
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.btnAdd)
    FloatingActionButton mBtnAdd;

    private BlockAdapter mBlockAdapter;
    private List<PhoneBlock> mPhoneBlocks;

    @Override
    public void inits() {
        SugarContext.init(getActivity());
        getData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mBlockAdapter = new BlockAdapter(getActivity(), mPhoneBlocks);
        mRecyclerView.setAdapter(mBlockAdapter);
    }

    @Click(R.id.btnAdd)
    void addAction() {
        showDialogAdd();
    }

    public void showDialogAdd() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_add_phone);
        dialog.setTitle("Add Phone");

        final EditText mEdtPhone = (EditText) dialog.findViewById(R.id.edtPhone);
        Button mBtnAdd = (Button) dialog.findViewById(R.id.btnAdd);
        Button mBtnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneBlock phoneBlock = new PhoneBlock(mEdtPhone.getText().toString());
                phoneBlock.save();
                dialog.dismiss();
                mPhoneBlocks.add(phoneBlock);
                mBlockAdapter.notifyDataSetChanged();
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void getData() {
        mPhoneBlocks = new ArrayList<>();
        mPhoneBlocks = PhoneBlock.listAll(PhoneBlock.class);
        Log.d("tag", mPhoneBlocks.size() + " 12 " + mPhoneBlocks.toString());
    }
}
