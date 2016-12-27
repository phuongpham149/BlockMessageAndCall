package com.example.phuong.blockmessageandcall.listeners;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;
import com.example.phuong.blockmessageandcall.models.PhoneBlock;
import com.orm.SugarContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuong on 27/12/2016.
 */

public class PhoneCallStateListener extends PhoneStateListener {
    private Context context;
    private List<PhoneBlock> mPhoneBlocks;

    public PhoneCallStateListener(Context context) {
        this.context = context;
        mPhoneBlocks = new ArrayList<>();
        SugarContext.init(context);
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {

        switch (state) {

            case TelephonyManager.CALL_STATE_RINGING:

                mPhoneBlocks = PhoneBlock.listAll(PhoneBlock.class);
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                //Turn ON the mute
                audioManager.setStreamMute(AudioManager.STREAM_RING, true);
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Class clazz = Class.forName(telephonyManager.getClass().getName());
                    Method method = clazz.getDeclaredMethod("getITelephony");
                    method.setAccessible(true);
                    ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);

                    for (PhoneBlock phoneBlock : mPhoneBlocks) {
                        if (incomingNumber.equalsIgnoreCase(phoneBlock.getPhone())) {
                            telephonyService = (ITelephony) method.invoke(telephonyManager);
                            telephonyService.silenceRinger();
                            telephonyService.endCall();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }
                //Turn OFF the mute
                audioManager.setStreamMute(AudioManager.STREAM_RING, false);
                break;
            case PhoneStateListener.LISTEN_CALL_STATE:
        }
        super.onCallStateChanged(state, incomingNumber);
    }
}
