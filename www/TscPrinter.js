var exec = require('cordova/exec');

exports.setupPrinter = function (args, success, error) {
    exec(success, error, 'TscPrinter', 'setupPrinter', args);
};

exports.connectPrinter = function (address, success, error) {
    exec(success, error, 'TscPrinter', 'connectPrinter', [address]);
};

exports.printBarCode = function (args, success, error) {
    exec(success, error, 'TscPrinter', 'printBarCode', args);
};