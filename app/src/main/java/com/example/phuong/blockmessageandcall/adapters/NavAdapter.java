package com.example.phuong.blockmessageandcall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.listeners.OnNavListener;
import com.example.phuong.blockmessageandcall.models.ItemNav;

import java.util.List;

/**
 * Created by phuong on 21/12/2016.
 */

public class NavAdapter extends RecyclerView.Adapter<NavAdapter.MyHolder> {
    private Context mContext;
    private List<ItemNav> mItemNavs;
    private OnNavListener mListener;

    public NavAdapter(Context context, List<ItemNav> itemNavs, OnNavListener listener) {
        mContext = context;
        mItemNavs = itemNavs;
        mListener = listener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_nav, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ItemNav itemNav = mItemNavs.get(position);
        holder.mTvTitle.setText(itemNav.getName());
        if (itemNav.getIcon() == 1) {
            holder.mImgIcon.setImageResource(R.drawable.ic_contact);
        } else {

            holder.mImgIcon.setImageResource(R.drawable.ic_message);
        }
    }

    @Override
    public int getItemCount() {
        return mItemNavs.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView mImgIcon;
        TextView mTvTitle;

        public MyHolder(final View itemView) {
            super(itemView);
            mImgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
            mTvTitle = (TextView) itemView.findViewById(R.id.tvTile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClickListener(getAdapterPosition());
                }
            });
        }
    }
}
