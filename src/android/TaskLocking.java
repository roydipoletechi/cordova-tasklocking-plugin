package com.dipoletechi.tasklocking;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


/**
 * This class echoes a string called from JavaScript.
 */
public class TaskLocking extends CordovaPlugin {

       private static final int REQUEST_ENABLE_LOCK_TASK = 1;
    private static final int REQUEST_DISABLE_LOCK_TASK = 2;

    private DevicePolicyManager devicePolicyManager;
    private Activity activity;
    private ComponentName adminComponent;

    // @Override
    // protected void pluginInitialize() {
    //     super.pluginInitialize();

    //     devicePolicyManager = (DevicePolicyManager) cordova.getActivity()
    //             .getSystemService(Context.DEVICE_POLICY_SERVICE);
    //     activity = cordova.getActivity();
    //     adminComponent = new ComponentName(activity, AdminReceiver.class);
    // }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("enable")) {
            enableLockTaskMode(callbackContext);
            return true;
        } else if (action.equals("disable")) {
            disableLockTaskMode(callbackContext);
            return true;
        }
        return false;
    }

    private void enableLockTaskMode(CallbackContext callbackContext) {
        // if (devicePolicyManager.isDeviceOwnerApp(activity.getPackageName())) {
            devicePolicyManager.setLockTaskPackages(adminComponent, new String[]{activity.getPackageName()});
            activity.startLockTask();
            callbackContext.success();
        // } else {
        //     requestAdminPermissions(REQUEST_ENABLE_LOCK_TASK, callbackContext);
        // }
    }

    private void disableLockTaskMode(CallbackContext callbackContext) {
        if (devicePolicyManager.isLockTaskPermitted(activity.getPackageName())) {
            activity.stopLockTask();
            callbackContext.success();
        } else {
            callbackContext.error("Lock task mode is not currently active.");
        }
    }

    private void requestAdminPermissions(int requestCode, CallbackContext callbackContext) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Enable lock task mode");
        cordova.startActivityForResult(this, intent, requestCode);
        callbackContext.success();
    }
}
