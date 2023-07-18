var exec = require('cordova/exec');

var TaskLockingPlugin = {
    enableTaskLocking: function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'TaskLockingPlugin', 'enableTaskLocking', []);
    }
};

module.exports = TaskLockingPlugin;