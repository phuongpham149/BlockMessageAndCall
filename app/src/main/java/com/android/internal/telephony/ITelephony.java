package com.android.internal.telephony;

/**
 * Created by phuong on 27/12/2016.
 */

public interface ITelephony {
    boolean endCall();

    void answerRingingCall();

    void silenceRinger();
}
