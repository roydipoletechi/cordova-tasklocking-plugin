package com.dipoletechi.tasklocking;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class TaskLockingPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("enableTaskLocking".equals(action)) {
            enableTaskLocking(callbackContext);
            return true;
        }
        return false;
    }

    private void enableTaskLocking(CallbackContext callbackContext) {
        Activity activity = cordova.getActivity();
        PackageManager packageManager = activity.getPackageManager();
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (packageManager.hasSystemFeature(PackageManager.FEATURE_HOME_SCREEN)) {
                Intent startLockTaskIntent = new Intent(activity, activity.getClass());
                startLockTaskIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(startLockTaskIntent);
                activityManager.moveTaskToFront(activity.getTaskId(), ActivityManager.MOVE_TASK_WITH_HOME);
                callbackContext.success();
            } else {
                callbackContext.error("Device does not support app pinning.");
            }
        } else {
            callbackContext.error("App pinning is only available on Android Marshmallow (6.0) and above.");
        }
    }
}
