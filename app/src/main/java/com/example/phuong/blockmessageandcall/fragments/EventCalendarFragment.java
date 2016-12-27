package com.example.phuong.blockmessageandcall.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.phuong.blockmessageandcall.R;
import com.example.phuong.blockmessageandcall.adapters.CalendarAdapter;
import com.example.phuong.blockmessageandcall.models.CalendarEvent;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by phuong on 26/12/2016.
 */
@EFragment(R.layout.fragment_block_message_list)
public class EventCalendarFragment extends BaseFragment {
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<CalendarEvent> mCalendarEvents;

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public void inits() {
        getData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CalendarAdapter calendarAdapter = new CalendarAdapter(getContext(), mCalendarEvents);
        mRecyclerView.setAdapter(calendarAdapter);
    }

    public void getData() {
        mCalendarEvents = new ArrayList<>();
        Cursor cursor = getActivity().getContentResolver().query(
                Uri.parse("content://com.android.calendar/events"),
                new String[]{"calendar_id", "title", "description",
                        "dtstart", "dtend", "eventLocation"}, null,
                null, null);
        cursor.moveToFirst();
        String[] CalNames = new String[cursor.getCount()];
        for (int i = 0; i < CalNames.length; i++) {
            CalendarEvent calendarEvent = new CalendarEvent(cursor.getString(1), getDate(Long.parseLong(cursor.getString(3))), getDate(Long.parseLong(cursor.getString(3))), cursor.getString(2));
            mCalendarEvents.add(calendarEvent);
            cursor.moveToNext();
        }
        cursor.close();
    }
}
