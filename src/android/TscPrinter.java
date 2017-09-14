package com.betterlife.tscprinter;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.tscdll.TSCActivity;

import java.lang.reflect.Array;

/**
 * This class echoes a string called from JavaScript.
 */
public class TscPrinter extends CordovaPlugin {

    private static final String TAG = "TSCPlugin";
    TSCActivity TscDll = new TSCActivity();

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.d(TAG, "Initializing TSCPlugin");
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("printBarCode")) {
            try {
                callbackContext.success(this.printBarCode(args));
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
            return true;
        }else if(action.equals("connectPrinter")){
            String macAddress = args.getString(0);
            try {
                callbackContext.success(this.connectPrinter(macAddress));
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
            return true;
        }else if(action.equals("setupPrinter")){
            try {
                callbackContext.success(this.setupPrinter(args));
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
            return true;
        }else if(action.equals("sendCommand")){
            try {
                callbackContext.success(this.sendCommand(args));
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
        }else if(action.equals("printLabel")){
            try {
                callbackContext.success(this.printLabel(args));
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
        }else if(action.equals("clearBuffer")){
            try {
                callbackContext.success(this.clearBuffer());
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
        }else if(action.equals("closeConnection")){
            try {
                callbackContext.success(this.disconnect());
            }catch (Error err){
                callbackContext.error(err.getMessage());
            }
        }
        return false;
    }


    private String connectPrinter(String macAddress){
        return TscDll.openport(macAddress);
    }

    private String setupPrinter(JSONArray args) throws JSONException {
        Integer width = args.getInt(0);
        Integer height = args.getInt(1);
        Integer speed = args.getInt(2);
        Integer density = args.getInt(3);
        Integer sensor = args.getInt(4);
        Integer sensor_distance = args.getInt(5);
        Integer sensor_offset = args.getInt(6);
        return TscDll.setup(width,height,speed,density,sensor,sensor_distance,sensor_offset);
    }

    private String printBarCode(JSONArray args) throws JSONException {
        Integer x = args.getInt(0);
        Integer y = args.getInt(1);
        String type = args.getString(2);
        Integer height = args.getInt(3);
        Integer human_readable = args.getInt(4);
        Integer rotation = args.getInt(5);
        Integer narrow = args.getInt(6);
        Integer wide = args.getInt(7);
        String content = args.getString(8);
        return TscDll.barcode(x,y,type,height,human_readable,rotation,narrow,wide,content);
        /*
                    TscDll.setup(70, 110, 4, 4, 0, 0, 0);
                    TscDll.clearbuffer();
                    TscDll.sendcommand("SET TEAR ON\n");
                    TscDll.sendcommand("SET COUNTER @1 1\n");
                    TscDll.sendcommand("@1 = \"0001\"\n");
                    TscDll.sendcommand("TEXT 100,300,\"3\",0,1,1,@1\n");
                    TscDll.barcode(100, 100, "128", 100, 1, 0, 3, 3, "123456789");
                    TscDll.printerfont(100, 250, "3", 0, 1, 1, "987654321");
                    TscDll.printlabel(2, 1);
        */
    }

    private String printLabel(JSONArray args) throws JSONException {
        Integer quantity = args.getInt(0);
        Integer copy = args.getInt(1);
        return TscDll.printlabel(quantity, copy);
    }

    private String sendCommand(JSONArray args) throws JSONException {
        String command = args.getString(0);
        return TscDll.sendcommand(command);
    }

    private String disconnect(){
        return TscDll.closeport();
    }

    private String getStatus(){
        return TscDll.status();
    }

    private String clearBuffer(){
        return TscDll.clearbuffer();
    }

}
