package com.zitlab.mobyra.library.util;

import android.content.Context;
import android.provider.Settings;
import com.zitlab.palmyra.util.BaseDevice;

public class DeviceDetails implements BaseDevice {

    private final Context context;

    public DeviceDetails(Context context) {
        this.context = context;
    }

    @Override
    public String getDeviceId() {
        String androidId = null;
        if (null != context) {
            androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return androidId;
    }
}
