package com.example.phuong.blockmessageandcall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.models.PhoneBlock;

import java.util.List;

/**
 * Created by phuong on 27/12/2016.
 */

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.MyHolder> {
    private List<PhoneBlock> mPhoneBlocks;
    private Context mContext;

    public BlockAdapter(Context mContext, List<PhoneBlock> mPhoneBlocks) {
        this.mContext = mContext;
        this.mPhoneBlocks = mPhoneBlocks;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_block_phone, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PhoneBlock phoneBlock = mPhoneBlocks.get(position);
        holder.mTvPhone.setText(phoneBlock.getPhone());
    }

    @Override
    public int getItemCount() {
        return mPhoneBlocks.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView mTvPhone;

        public MyHolder(View itemView) {
            super(itemView);
            mTvPhone = (TextView) itemView.findViewById(R.id.tvPhoneBlock);
        }
    }
}
