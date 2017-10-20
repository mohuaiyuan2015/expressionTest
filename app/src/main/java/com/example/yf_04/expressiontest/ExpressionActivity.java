package com.example.yf_04.expressiontest;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yf_04.expressiontest.BlueToothLeService.BluetoothLeService;
import com.example.yf_04.expressiontest.Utils.Constants;
import com.example.yf_04.expressiontest.Utils.GattAttributes;
import com.example.yf_04.expressiontest.Utils.MyLog;
import com.example.yf_04.expressiontest.Utils.Orders;
import com.example.yf_04.expressiontest.Utils.Utils;
import com.example.yf_04.expressiontest.mediaplayer.MediaPlayerManager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.yf_04.expressiontest.BlueToothLeService.BluetoothLeService.requestMtu;

public class ExpressionActivity extends AppCompatActivity {

    private static final String TAG = "ExpressionActivity";

    public static final String CONNECT_MODEL="connect_model";
    public static final Integer CONNECT_MODEL_SINGLE=1;
    public static final Integer CONNECT_MODEL_MULTIPLe=2;
    public static final Integer CONNECT_MODEL_DEFAULT=CONNECT_MODEL_SINGLE;
    public static final Integer CHARACTERISTIC_TYPE_WRITE=1;
    public static final Integer CHARACTERISTIC_TYPE_NOTIFY=2;

    private Button action1;
    private Button action2;
    private Button action3;
    private Button action4;
    private Button action5;

    private Button action6;
    private Button action7;
    private Button action8;
    private Button action9;
    private Button action10;

    private Button action11;
    private Button action12;
    private Button action13;
    private Button action14;
    private Button action15;

    private Button action16;
    private Button action17;
    private Button action18;
    private Button action19;
    private Button action20;


    private Button action21;
    private Button action22;
    private Button action23;
    private Button action24;
    private Button action25;

    private Button action26;
    private Button action27;
    private Button action28;
    private Button action29;
    private Button action30;

    private Button action31;
    private Button action32;
    private Button action33;
    private Button action34;
    private Button action35;

    private Button action36;
    private Button action37;
    private Button action38;
    private Button action39;
    private Button action40;

    private Button action41;
    private Button action42;
    private Button action43;




    private Button awkward;
    private Button naughty;
    private Button shy;
    private Button cry;
    private Button disgust;
    private Button angry;
    private Button fear;
    private Button confused;
    private Button love;
    private Button clam;
    private Button serious;

    private Boolean nofityEnable=false;

    private BluetoothGattCharacteristic notifyCharacteristic;
    private BluetoothGattCharacteristic writeCharacteristic;
    private BluetoothGattCharacteristic readCharacteristic;
    private MyApplication myApplication;
    private Context context;

    private int sdkInt;
    private  Boolean isDebugMode=false;

    private MyHandler myHandler;

    private MediaPlayerManager mediaPlayerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicate_expression_layout);

        context=this;
        myHandler=new MyHandler(context);
        myApplication = (MyApplication) getApplication();

        mediaPlayerManager=new MediaPlayerManager(context);

        initCharacteristicWithMultiple();

        MyLog.debug(TAG, "USR_SERVICE charac...: "+ GattAttributes.USR_SERVICE);
        MyLog.debug(TAG, "write charac...: "+writeCharacteristic.getUuid().toString());
        MyLog.debug(TAG, "notify charac...: "+notifyCharacteristic.getUuid().toString());


        initUI();
        initListener();

        //mohuaiyuan 201707  暂时注释  仅仅为了测试 发送数据的情况
        sdkInt = Build.VERSION.SDK_INT;
        requestMtu(512);


    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, Utils.makeGattUpdateIntentFilter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopNotifyOrIndicate();
        unregisterReceiver(mGattUpdateReceiver);
    }

    public static final int SEND_ORDER_SUCCESS=0;
    public static final int SEND_ORDER_FAILED=1;

    class MyHandler extends android.os.Handler{

        private Context context;
        public MyHandler(Context context){
            this.context=context;
        }

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SEND_ORDER_FAILED:
                    Toast.makeText(context, context.getResources().getString(R.string.send_failed), Toast.LENGTH_SHORT).show();
                    break;

                default:

            }

        }
    }

    private void initListener() {

        action1.setOnClickListener(onClickListener);
        action2.setOnClickListener(onClickListener);
        action3.setOnClickListener(onClickListener);
        action4.setOnClickListener(onClickListener);
        action5.setOnClickListener(onClickListener);

        action6.setOnClickListener(onClickListener);
        action7.setOnClickListener(onClickListener);
        action8.setOnClickListener(onClickListener);
        action9.setOnClickListener(onClickListener);
        action10.setOnClickListener(onClickListener);

        action11.setOnClickListener(onClickListener);
        action12.setOnClickListener(onClickListener);
        action13.setOnClickListener(onClickListener);
        action14.setOnClickListener(onClickListener);
        action15.setOnClickListener(onClickListener);

        action16.setOnClickListener(onClickListener);
        action17.setOnClickListener(onClickListener);
        action18.setOnClickListener(onClickListener);
        action19.setOnClickListener(onClickListener);
        action20.setOnClickListener(onClickListener);

        action21.setOnClickListener(onClickListener);
        action22.setOnClickListener(onClickListener);
        action23.setOnClickListener(onClickListener);
        action24.setOnClickListener(onClickListener);
        action25.setOnClickListener(onClickListener);

        action26.setOnClickListener(onClickListener);
        action27.setOnClickListener(onClickListener);
        action28.setOnClickListener(onClickListener);
        action29.setOnClickListener(onClickListener);
        action30.setOnClickListener(onClickListener);

        action31.setOnClickListener(onClickListener);
        action32.setOnClickListener(onClickListener);
        action33.setOnClickListener(onClickListener);
        action34.setOnClickListener(onClickListener);
        action35.setOnClickListener(onClickListener);

        action36.setOnClickListener(onClickListener);
        action37.setOnClickListener(onClickListener);
        action38.setOnClickListener(onClickListener);
        action39.setOnClickListener(onClickListener);
        action40.setOnClickListener(onClickListener);

        action41.setOnClickListener(onClickListener);
        action42.setOnClickListener(onClickListener);
        action43.setOnClickListener(onClickListener);



        awkward.setOnClickListener(onClickListener);
        naughty.setOnClickListener(onClickListener);
        shy.setOnClickListener(onClickListener);
        cry.setOnClickListener(onClickListener);
        disgust.setOnClickListener(onClickListener);
        angry.setOnClickListener(onClickListener);
        fear.setOnClickListener(onClickListener);
        confused.setOnClickListener(onClickListener);
        love.setOnClickListener(onClickListener);
        clam.setOnClickListener(onClickListener);
        serious.setOnClickListener(onClickListener);

    }

    private void initUI() {

        action1=(Button)findViewById(R.id.action1);
        action2=(Button)findViewById(R.id.action2);
        action3=(Button)findViewById(R.id.action3);
        action4=(Button)findViewById(R.id.action4);
        action5=(Button)findViewById(R.id.action5);

        action6=(Button)findViewById(R.id.action6);
        action7=(Button)findViewById(R.id.action7);
        action8=(Button)findViewById(R.id.action8);
        action9=(Button)findViewById(R.id.action9);
        action10=(Button)findViewById(R.id.action10);

        action11=(Button)findViewById(R.id.action11);
        action12=(Button)findViewById(R.id.action12);
        action13=(Button)findViewById(R.id.action13);
        action14=(Button)findViewById(R.id.action14);
        action15=(Button)findViewById(R.id.action15);

        action16=(Button)findViewById(R.id.action16);
        action17=(Button)findViewById(R.id.action17);
        action18=(Button)findViewById(R.id.action18);
        action19=(Button)findViewById(R.id.action19);
        action20=(Button)findViewById(R.id.action20);

        action21=(Button)findViewById(R.id.action21);
        action22=(Button)findViewById(R.id.action22);
        action23=(Button)findViewById(R.id.action23);
        action24=(Button)findViewById(R.id.action24);
        action25=(Button)findViewById(R.id.action25);

        action26=(Button)findViewById(R.id.action26);
        action27=(Button)findViewById(R.id.action27);
        action28=(Button)findViewById(R.id.action28);
        action29=(Button)findViewById(R.id.action29);
        action30=(Button)findViewById(R.id.action30);

        action31=(Button)findViewById(R.id.action31);
        action32=(Button)findViewById(R.id.action32);
        action33=(Button)findViewById(R.id.action33);
        action34=(Button)findViewById(R.id.action34);
        action35=(Button)findViewById(R.id.action35);

        action36=(Button)findViewById(R.id.action36);
        action37=(Button)findViewById(R.id.action37);
        action38=(Button)findViewById(R.id.action38);
        action39=(Button)findViewById(R.id.action39);
        action40=(Button)findViewById(R.id.action40);

        action41=(Button)findViewById(R.id.action41);
        action42=(Button)findViewById(R.id.action42);
        action43=(Button)findViewById(R.id.action43);


        awkward= (Button) findViewById(R.id.awkward);
        naughty= (Button) findViewById(R.id.naughty);
        shy= (Button) findViewById(R.id.shy);
        cry= (Button) findViewById(R.id.cry);
        disgust= (Button) findViewById(R.id.disgust);
        angry= (Button) findViewById(R.id.angry);
        fear= (Button) findViewById(R.id.fear);
        confused= (Button) findViewById(R.id.confused);
        love= (Button) findViewById(R.id.love);
        clam= (Button) findViewById(R.id.clam);
        serious= (Button) findViewById(R.id.serious);

    }

    private SendOrderRunnable.SendOrderResult sendOrderResult= new SendOrderRunnable.SendOrderResult() {
        @Override
        public void getResult(int responseCode) {
            android.os.Message message=new android.os.Message();
            message.what=responseCode;
            myHandler.sendMessage(message);
        }
    };

    private String order=null;
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                //----------机器人拍摄所用动作--------------------------------
                case R.id.action1:
                    order= Orders.ACTION1;
                    writeOption(order);
                    break;

                case R.id.action2:
                    order=Orders.ACTION2;
                    writeOption(order);
                    break;

                case R.id.action3:
                    order= Orders.ACTION3;
                    writeOption(order);
                    break;

                case R.id.action4:
                    order=Orders.ACTION4;
                    writeOption(order);
                    break;

                case R.id.action5:
                    order=Orders.ACTION5;
                    writeOption(order);
                    break;

                case R.id.action6:
                    order=Orders.ACTION6;
                    writeOption(order);
                    break;

                case R.id.action7:
                    order=Orders.ACTION7;
                    writeOption(order);
                    break;

                case R.id.action8:
                    order=Orders.ACTION8;
                    writeOption(order);
                    break;

                case R.id.action9:
                    order=Orders.ACTION9;
                    writeOption(order);
                    break;

                case R.id.action10:
                    order=Orders.ACTION10;
                    writeOption(order);
                    break;

                case R.id.action11:
                    order= Orders.ACTION11;
                    writeOption(order);
                    break;

                case R.id.action12:
                    order=Orders.ACTION12;
                    writeOption(order);
                    break;

                case R.id.action13:
                    order= Orders.ACTION13;
                    writeOption(order);
                    break;

                case R.id.action14:
                    order=Orders.ACTION14;
                    writeOption(order);
                    break;

                case R.id.action15:
                    order=Orders.ACTION15;
                    writeOption(order);
                    break;

                case R.id.action16:
                    order=Orders.ACTION16;
                    writeOption(order);
                    break;

                case R.id.action17:
                    order=Orders.ACTION17;
                    writeOption(order);
                    break;

                case R.id.action18:
                    order=Orders.ACTION18;
                    writeOption(order);
                    break;

                case R.id.action19:
                    order=Orders.ACTION19;
                    writeOption(order);
                    break;

                case R.id.action20:
                    order=Orders.ACTION20;
                    writeOption(order);
                    break;

                case R.id.action21:
                    order= Orders.ACTION21;
                    writeOption(order);
                    break;

                case R.id.action22:
                    order=Orders.ACTION22;
                    writeOption(order);
                    break;

                case R.id.action23:
                    order= Orders.ACTION23;
                    writeOption(order);
                    break;

                case R.id.action24:
                    order=Orders.ACTION24;
                    writeOption(order);
                    break;

                case R.id.action25:
                    order=Orders.ACTION25;
                    writeOption(order);
                    break;

                case R.id.action26:
                    order=Orders.ACTION26;
                    writeOption(order);
                    break;

                case R.id.action27:
                    order=Orders.ACTION27;
                    writeOption(order);
                    break;

                case R.id.action28:
                    order=Orders.ACTION28;
                    writeOption(order);
                    break;

                case R.id.action29:
                    order=Orders.ACTION29;
                    writeOption(order);
                    break;

                case R.id.action30:
                    order=Orders.ACTION30;
                    writeOption(order);
                    break;

                case R.id.action31:
                    order= Orders.ACTION31;
                    writeOption(order);
                    break;

                case R.id.action32:
                    order=Orders.ACTION32;
                    writeOption(order);
                    break;

                case R.id.action33:
                    order= Orders.ACTION33;
                    writeOption(order);
                    break;

                case R.id.action34:
                    order=Orders.ACTION34;
                    writeOption(order);
                    break;

                case R.id.action35:
                    order=Orders.ACTION35;
                    writeOption(order);
                    break;

                case R.id.action36:
                    order=Orders.ACTION36;
                    writeOption(order);
                    break;

                case R.id.action37:
                    order=Orders.ACTION37;
                    writeOption(order);
                    break;

                case R.id.action38:
                    order=Orders.ACTION38;
                    writeOption(order);
                    break;

                case R.id.action39:
                    order=Orders.ACTION39;
                    writeOption(order);
                    break;

                case R.id.action40:
                    order=Orders.ACTION40;
                    writeOption(order);
                    break;

                case R.id.action41:
                    order=Orders.ACTION41;
                    writeOption(order);
                    break;

                case R.id.action42:
                    order=Orders.ACTION42;
                    writeOption(order);
                    break;

                case R.id.action43:
                    order=Orders.ACTION43;
                    writeOption(order);
                    break;





                //-----------这部分是表情 指令---------------------------
                case R.id.awkward:
                    order= Orders.AWKWARD;
                    writeOption(order);
                    break;

                case R.id.naughty:
                    order= Orders.NAUGHTY;
                    writeOption(order);
                    break;

                case R.id.shy:
                    order= Orders.SHY;
                    writeOption(order);
                    break;

                case R.id.cry:
                    order= Orders.CRY;
                    writeOption(order);
                    break;

                case R.id.disgust:
                    order= Orders.DISGUST;
                    writeOption(order);
                    break;

                case R.id.angry:
                    order= Orders.ANGRY;
                    writeOption(order);
                    break;

                case R.id.fear:
                    order= Orders.FEAR;
                    writeOption(order);
                    break;

                case R.id.confused:
                    order= Orders.CONFUSED;
                    writeOption(order);
                    break;

                case R.id.love:
                    order= Orders.LOVE ;
                    writeOption(order);
                    break;

                case R.id.clam:
                    order= Orders.CLAM;
                    writeOption(order);
                    break;

                case R.id.serious:
                    order= Orders.SERIOUS;
                    writeOption(order);
                    break;

                default:

            }


        }
    };


    private void writeOption( String order) {
        MyLog.debug(TAG, "writeOption: ");

        if (TextUtils.isEmpty(order)) {
            MyLog.error(TAG, "writeOption:order is empty!!!");
            return;
        }

        //对十六进制的数据进行处理
        order = order.replace(" ", "");
        if (!Utils.isRightHexStr(order)) {
            return;
        }

        //mohuaiyuan
//        writeCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);

        Map<String,Map<Integer,BluetoothGattCharacteristic>> currentCharacteristic =ConnectionInfoCollector.getCurrentCharacteristic();
        Iterator<String> iterator = currentCharacteristic.keySet().iterator();
        BluetoothGatt bluetoothGatt;
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        while (iterator.hasNext()) {
            String deviceAddress = iterator.next();
            Log.d(TAG, "deviceAddress: "+deviceAddress);
            bluetoothGatt=ConnectionInfoCollector.getBluetoothGattMap().get(deviceAddress);
            Map<Integer, BluetoothGattCharacteristic> map = currentCharacteristic.get(deviceAddress);
            bluetoothGattCharacteristic = map.get(CHARACTERISTIC_TYPE_WRITE);
            writeOption(bluetoothGatt,bluetoothGattCharacteristic,order);

        }
    }

    private void writeOption(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic characteristic, String order){
        MyLog.debug(TAG, "writeOption: ");

        if (TextUtils.isEmpty(order)){
            MyLog.error(TAG, "writeOption:order is empty!!!" );
            return;
        }

        //对十六进制的数据进行处理
        order = order.replace(" ","");
        if (!Utils.isRightHexStr(order)){
            return;
        }

        //mohuaiyuan
//        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);


        MyLog.debug(TAG, "sdkInt: "+sdkInt);
        if (sdkInt >= 21) {
            byte[] array = Utils.hexStringToByteArray(order);
            writeCharacteristic(bluetoothGatt,characteristic, array);
        } else {
//            SendOrderRunnable myRunnable = new SendOrderRunnable(order, characteristic);
            SendOrderRunnable myRunnable = new SendOrderRunnable(order, characteristic,bluetoothGatt);
            myRunnable.setSendOrderResult(sendOrderResult);
            Thread thread = new Thread(myRunnable);
            thread.start();
        }

    }

    public void writeCharacteristic(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic characteristic, byte[] bytes) {
        Log.d(TAG, "writeCharacteristic: ");
        // Writing the hexValue to the characteristics
        try {
            BluetoothLeService.writeCharacteristicGattDb(bluetoothGatt,characteristic, bytes);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void initCharacteristicWithMultiple(){
        MyLog.debug(TAG, "initCharacteristicWithMultiple: ");

        Map<String, List<BluetoothGattCharacteristic>> map=ConnectionInfoCollector.getCharacteristicsMap();
        Iterator<String>iterator=map.keySet().iterator();

        while (iterator.hasNext()) {
            String deviceAddress = iterator.next();
            BluetoothGattCharacteristic characteristic = ConnectionInfoCollector.getCurrentCharacteristic().get(deviceAddress).get(CHARACTERISTIC_TYPE_WRITE);
            List<BluetoothGattCharacteristic> characteristics = map.get(deviceAddress);
            MyLog.debug(TAG, "deviceAddress : "+deviceAddress);

            for (BluetoothGattCharacteristic c : characteristics) {
                if (Utils.getProperties(context, c).equals("Notify")) {
                    MyLog.debug(TAG, "there is a notify characteristics............ : ");
                    MyLog.debug(TAG, "uuid : "+c.getUuid().toString()+" property:"+c.getProperties());
                    notifyCharacteristic = c;
                    ConnectionInfoCollector.getCurrentCharacteristic().get(deviceAddress).put(CHARACTERISTIC_TYPE_NOTIFY,c);
                    continue;
                }

                if (Utils.getProperties(context, c).equals("Write")) {
                    MyLog.debug(TAG, "there is a write characteristics............ : ");
                    MyLog.debug(TAG, "uuid : "+c.getUuid().toString()+" property:"+c.getProperties());
                    writeCharacteristic = c;
                    ConnectionInfoCollector.getCurrentCharacteristic().get(deviceAddress).put(CHARACTERISTIC_TYPE_WRITE,c);
                    continue;
                }
            }

            if (notifyCharacteristic == null) {
                MyLog.error(TAG, "notifyCharacteristic == null" );
                notifyCharacteristic = characteristic;
                ConnectionInfoCollector.getCurrentCharacteristic().get(deviceAddress).put(CHARACTERISTIC_TYPE_NOTIFY,characteristic);
            }
            if (writeCharacteristic == null) {
                MyLog.error(TAG, "writeCharacteristic == null " );
                writeCharacteristic = characteristic;
                ConnectionInfoCollector.getCurrentCharacteristic().get(deviceAddress).put(CHARACTERISTIC_TYPE_WRITE,characteristic);
            }
        }
    }

    private void requestMtu(int mtu) {
        MyLog.debug(TAG, "requestMtu: ");

        MyLog.debug(TAG, "sdkInt------------>" + sdkInt);

        Map<String,BluetoothGatt> bluetoothGattMap=ConnectionInfoCollector.getBluetoothGattMap();
        Iterator<String> iterator =bluetoothGattMap.keySet().iterator();
        BluetoothGatt bluetoothGatt;

        while (iterator.hasNext()) {
            String deveceAddress = iterator.next();
            bluetoothGatt=bluetoothGattMap.get(deveceAddress);

            requestMtu(bluetoothGatt,mtu);

        }

    }

    private void requestMtu(BluetoothGatt bluetoothGatt,int mtu ){
        MyLog.debug(TAG, "requestMtu: ");
        MyLog.debug(TAG, "sdkInt------------>"+sdkInt);
        if (sdkInt>=21){
            //设置最大发包、收包的长度为512个字节
            if(BluetoothLeService.requestMtu(bluetoothGatt,mtu)){
                MyLog.debug(TAG, "Max transmittal data is 512 ");
            }else{
                MyLog.debug(TAG, "Max transmittal data is 20 ");
            }

        }else {
            MyLog.debug(TAG, "Max transmittal data is 20 ");
//            Toast.makeText(this,getString(R.string.transmittal_length,"20"),Toast.LENGTH_LONG).show();
        }

    }

    private BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            MyLog.debug(TAG, "Communicate mGattUpdateReceiver action: "+action);
            Bundle extras = intent.getExtras();
            if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                // Data Received
                if (extras.containsKey(Constants.EXTRA_BYTE_VALUE)) {
                    if (extras.containsKey(Constants.EXTRA_BYTE_UUID_VALUE)) {
                        if (myApplication != null) {
                            BluetoothGattCharacteristic requiredCharacteristic = myApplication.getCharacteristic();
                            String uuidRequired = requiredCharacteristic.getUuid().toString();
                            String receivedUUID = intent.getStringExtra(Constants.EXTRA_BYTE_UUID_VALUE);

                            if (isDebugMode){
                                byte[] array = intent.getByteArrayExtra(Constants.EXTRA_BYTE_VALUE);
                                MyLog.debug(TAG, "array : "+array.toString());
                            }else if (uuidRequired.equalsIgnoreCase(receivedUUID)) {
                                byte[] array = intent.getByteArrayExtra(Constants.EXTRA_BYTE_VALUE);
                                MyLog.debug(TAG, "array : "+array.toString());
                            }
                        }
                    }
                }

            }

        }
    };


}
