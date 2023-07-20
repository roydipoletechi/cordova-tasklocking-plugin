package com.dipoletechi.tasklocking;


import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

public class AdminReceiver extends DeviceAdminReceiver {

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Disabling admin permissions will remove lock task mode. Are you sure?";
    }

}