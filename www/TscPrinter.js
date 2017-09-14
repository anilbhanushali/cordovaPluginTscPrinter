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

exports.sendCommand = function (args, success, error) {
    exec(success, error, 'TscPrinter', 'sendCommand', args);
};

exports.printLabel = function (args, success, error) {
    exec(success, error, 'TscPrinter', 'printLabel', args);
};

exports.clearBuffer = function (args, success, error) {
    exec(success, error, 'TscPrinter', 'clearBuffer', args);
};

exports.closeConnection = function (args, success, error) {
    exec(success, error, 'TscPrinter', 'closeConnection', args);
};