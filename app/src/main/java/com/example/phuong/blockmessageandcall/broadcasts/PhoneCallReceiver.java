package com.example.phuong.blockmessageandcall.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.example.phuong.blockmessageandcall.listeners.PhoneCallStateListener;

/**
 * Created by phuong on 27/12/2016.
 */

public class PhoneCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        PhoneCallStateListener customPhoneListener = new PhoneCallStateListener(context);
        telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
