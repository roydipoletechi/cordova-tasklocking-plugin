var exec = require('cordova/exec');

// var TaskLocking = {
//     // Enable lock task mode
//     enableLockTaskMode: function enableLockTaskMode(successCallback,errorCallback) {
//         if (cordova.platformId === 'android') {
//             cordova.exec(successCallback, errorCallback, 'LockTaskMode', 'enable', []);
//         }
//     },

//     // Disable lock task mode
//     disableLockTaskMode: function disableLockTaskMode() {
//         if (cordova.platformId === 'android') {
//             cordova.exec(successCallback, errorCallback, 'LockTaskMode', 'disable', []);
//         }
//     }
// };

// module.exports = TaskLocking;

var ScreenPinning = {

    /**
     * enterPinnedMode()
     */
    enterPinnedMode: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, "ScreenPinning", "enterPinnedMode", []);
    },

    /**
     * exitPinnedMode()
     */
    exitPinnedMode: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, "ScreenPinning", "exitPinnedMode", []);
    }

};

module.exports = ScreenPinning; 