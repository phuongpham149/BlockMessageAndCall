package com.example.phuong.blockmessageandcall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.models.CalendarEvent;

import java.util.List;

/**
 * Created by phuong on 27/12/2016.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyHolder> {
    private Context mContext;
    private List<CalendarEvent> mCalendarEvents;

    public CalendarAdapter(Context mContext, List<CalendarEvent> mCalendarEvents) {
        this.mContext = mContext;
        this.mCalendarEvents = mCalendarEvents;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CalendarEvent calendarEvent = mCalendarEvents.get(position);
        holder.mTvName.setText(calendarEvent.getName());
        holder.mTvStart.setText(calendarEvent.getStart().toString());
        holder.mTvEnd.setText(calendarEvent.getEnd().toString());
        holder.mTvDes.setText(calendarEvent.getDescription());
    }

    @Override
    public int getItemCount() {
        return mCalendarEvents.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvStart;
        TextView mTvEnd;
        TextView mTvDes;


        public MyHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tvName);
            mTvStart = (TextView) itemView.findViewById(R.id.tvStart);
            mTvEnd = (TextView) itemView.findViewById(R.id.tvEnd);
            mTvDes = (TextView) itemView.findViewById(R.id.tvDescription);
        }
    }
}
