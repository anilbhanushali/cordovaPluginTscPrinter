var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, "TscPrinter", "coolMethod", [arg0]);
};

exports.connectPrinter = function (address, successCallback, errorCallback) {
    cordova.exec(
        successCallback, // success callback function
        errorCallback, // error callback function
        'Printer', // mapped to our native Java class called "Printer"
        'connect', // with this action name
        [{                  // and this array of custom arguments to create our entry
            "macaddress": address
        }]
    );
};

exports.printText = function (macaddress, text, alignment, attribute, textsize, successCallback, errorCallback) {
    cordova.exec(
        successCallback, // success callback function
        errorCallback, // error callback function
        'Printer', // mapped to our native Java class called "Printer"
        'printtext', // with this action name
        [{
            "macaddress": macaddress,// and this array of custom arguments to create our entry
            "text": text,
            "alignment": alignment,
            "attribute": attribute,
            "textsize": textsize
        }]
    );
};

exports.PrintImage = function (macaddress, file, successCallback, errorCallback) {
    cordova.exec(
        successCallback, // success callback function
        errorCallback, // error callback function
        'Printer', // mapped to our native Java class called "Printer"
        'printimage', // with this action name
        [{
            "macaddress": macaddress,// and this array of custom arguments to create our entry
            "file": file
        }]
    );
}