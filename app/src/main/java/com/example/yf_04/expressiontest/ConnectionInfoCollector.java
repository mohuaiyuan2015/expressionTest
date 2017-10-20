package com.example.yf_04.expressiontest;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;

import com.example.yf_04.expressiontest.Utils.MyLog;
import com.example.yf_04.expressiontest.bean.MService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by YF-04 on 2017/8/14.
 */

public class ConnectionInfoCollector {

    private static final String TAG = "ConnectionInfoCollector";

    private static Map<String,BluetoothGatt> bluetoothGattMap=new HashMap<>();
    private static Map<String ,List<MService>>  servicesMap = new HashMap<>();
    private static Map<String ,List<BluetoothGattCharacteristic>>  characteristicsMap=new HashMap<>() ;
    private static Map<String,Map<Integer,BluetoothGattCharacteristic>> currentCharacteristic=new HashMap<>();

    public static Map<String, BluetoothGatt> getBluetoothGattMap() {
        return bluetoothGattMap;
    }

    public static void setBluetoothGattMap(Map<String, BluetoothGatt> bluetoothGattMap) {
        ConnectionInfoCollector.bluetoothGattMap = bluetoothGattMap;
    }

    public static BluetoothGatt getBluetoothGatt(String deviceAddress){
        BluetoothGatt bluetoothGatt=null;
        if(deviceAddress==null || deviceAddress.isEmpty()){
            return bluetoothGatt;
        }
        bluetoothGatt=bluetoothGattMap.get(deviceAddress);
        return bluetoothGatt;

    }

    public static BluetoothGatt putBluetoothGatt(String deviceAddress,BluetoothGatt bluetoothGatt){
        if(deviceAddress==null  || deviceAddress.isEmpty()|| bluetoothGatt==null){
            return null;
        }
        BluetoothGatt oldValue=bluetoothGattMap.put(deviceAddress,bluetoothGatt);

        return oldValue;

    }

    public static BluetoothGatt addBluetoothGatt(BluetoothGatt bluetoothGatt){
        BluetoothGatt result=null;
        if(bluetoothGatt==null){
            return result ;
        }
        BluetoothDevice device=bluetoothGatt.getDevice();
        if(device==null){
            return result;
        }
        String deviceAddress=device.getAddress();
        if(deviceAddress==null){
            return result;
        }
        result=putBluetoothGatt(deviceAddress,bluetoothGatt);
        return result;

    }

    public static void remove(String deviceAddress) {
        MyLog.debug(TAG, "remove: ");

        bluetoothGattMap.remove(deviceAddress);
        servicesMap.remove(deviceAddress);
        characteristicsMap.remove(deviceAddress);
        currentCharacteristic.remove(deviceAddress);

    }


    public static Map<String, List<MService>> getServicesMap() {
        return servicesMap;
    }

    public static void setServicesMap(Map<String, List<MService>> servicesMap) {
        ConnectionInfoCollector.servicesMap = servicesMap;
    }

    public static Map<String, List<BluetoothGattCharacteristic>> getCharacteristicsMap() {
        return characteristicsMap;
    }

    public static void setCharacteristicsMap(Map<String, List<BluetoothGattCharacteristic>> characteristicsMap) {
        ConnectionInfoCollector.characteristicsMap = characteristicsMap;
    }

    public static Map<String, Map<Integer, BluetoothGattCharacteristic>> getCurrentCharacteristic() {
        return currentCharacteristic;
    }

    public static void setCurrentCharacteristic(Map<String, Map<Integer, BluetoothGattCharacteristic>> currentCharacteristic) {
        ConnectionInfoCollector.currentCharacteristic = currentCharacteristic;
    }



    public static String print(){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("[bluetoothGattMap :"+bluetoothGattMap.toString()+"]");
        stringBuffer.append("[servicesMap :"+servicesMap.toString()+"]");
        stringBuffer.append("[characteristicsMap :"+characteristicsMapToString()+"]");
        stringBuffer.append("[currentCharacteristic :"+currentCharacteristicToString()+"]");


        return stringBuffer.toString();
    }

    private static String characteristicsMapToString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> iterator = characteristicsMap.keySet().iterator();
        while (iterator.hasNext()) {
            stringBuffer.append("[");
            String deviceAddress = iterator.next();
            stringBuffer.append(deviceAddress + "{");
            List<BluetoothGattCharacteristic> characteristics = characteristicsMap.get(deviceAddress);
            for (int i = 0; i < characteristics.size(); i++) {
                BluetoothGattCharacteristic characteristic = characteristics.get(i);
                String uuid = characteristic.getUuid().toString();
                int property = characteristic.getProperties();
                stringBuffer.append("uuid=" + uuid + "  property=" + property + ";");

            }

            stringBuffer.append("} ]");
        }


        return stringBuffer.toString();
    }
    private static String currentCharacteristicToString(){
        StringBuffer stringBuffer=new StringBuffer();
        Iterator<String>iterator= currentCharacteristic.keySet().iterator();

        while (iterator.hasNext()){
            stringBuffer.append("[");
            String deviceAddress=iterator.next();
            stringBuffer.append(deviceAddress+"{");
            Map<Integer,BluetoothGattCharacteristic> map=currentCharacteristic.get(deviceAddress);
            Iterator<Integer> charIterator=map.keySet().iterator();
            while (charIterator.hasNext()){
                Integer type=charIterator.next();
                BluetoothGattCharacteristic charac=map.get(type);
                stringBuffer.append(type+"="+charac.getUuid().toString()+";");
            }

            stringBuffer.append("} ]");
        }

        return stringBuffer.toString();
    }

}
