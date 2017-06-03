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
        TscDll.barcode(x,y,type,height,human_readable,rotation,narrow,wide,content);
        return this.printLabel(1,1);
    }

    private String printLabel(Integer quantity,Integer copy){
        return TscDll.printlabel(quantity, copy);
    }

    private String Disconnect(){
        return TscDll.closeport();
    }

    private String getStatus(){
        return TscDll.status();
    }

}
