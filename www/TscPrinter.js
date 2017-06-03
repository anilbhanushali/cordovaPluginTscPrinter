var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, "TscPrinter", "coolMethod", [arg0]);
};

exports.connectPrinter = function (address, successCallback, errorCallback) {
    cordova.exec(
        successCallback, // success callback function
        errorCallback, // error callback function
        'TscPrinter',
        'connectPrinter', // with this action name
        [address]
    );
};

exports.printBarCode = function (args, successCallback, errorCallback) {
    cordova.exec(
        successCallback, // success callback function
        errorCallback, // error callback function
        'TscPrinter',
        'printBarCode',
        args
    );
};