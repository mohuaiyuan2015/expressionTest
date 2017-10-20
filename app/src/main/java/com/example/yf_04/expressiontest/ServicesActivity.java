package com.example.yf_04.expressiontest;

import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yf_04.expressiontest.R;
import com.example.yf_04.expressiontest.Utils.GattAttributes;
import com.example.yf_04.expressiontest.adapter.ServicesAdapter;
import com.example.yf_04.expressiontest.bean.MService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//mohuaiyuan 201707
//import butterknife.Bind;

public class ServicesActivity extends AppCompatActivity {

    private static final String TAG = "ServicesActivity";

    private final List<MService> list = new ArrayList<>();

    //mohuaiyuan 201707
//    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    //mohuaiyuan 201707
//    @Bind(R.id.view_filter)
    View filterView;
    //mohuaiyuan 201707
//    @Bind(R.id.view_shadow)
    View viewShadow;
    //mohuaiyuan 201707
//    @Bind(R.id.lv_services)
    ListView lvServices;
    //mohuaiyuan 201707
//    @Bind(R.id.iv_ble)
    ImageView ivBle;
    //mohuaiyuan 201707
//    @Bind(R.id.tv_service_name)
    TextView tvServiceName;
    //mohuaiyuan 201707
//    @Bind(R.id.tv_service_mac)
    TextView tvServiceMac;
    //mohuaiyuan 201707
//    @Bind(R.id.tv_service_count)
    TextView tvServiceCount;

    private ServicesAdapter adapter;


    private MyApplication myApplication;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Log.d(TAG, "onCreate: ");
        context=this;
        
        initUI();

        //mohuaiyuan 201707
//        bindToolBar();
        myApplication =  (MyApplication)getApplication();
        List<MService> services =myApplication.getServices();
        Log.d(TAG, "services.size(): "+services.size());
        list.addAll(services);


        //mohuaiyuan 201707
        adapter = new ServicesAdapter(this, list);
        lvServices.setAdapter(adapter);

        Intent intent = getIntent();
        tvServiceName.setText("NAME:"+intent.getStringExtra("dev_name"));
        tvServiceMac.setText("MAC:"+intent.getStringExtra("dev_mac"));
        tvServiceCount.setText("SERVICES:"+ String.valueOf(list.size()));


        initListener();


        if (savedInstanceState == null) {
            filterView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    filterView.getViewTreeObserver().removeOnPreDrawListener(this);
//                    startAnimation();
                    return true;
                }
            });
        }

//        itemClick();


    }

   private void initListener() {

        lvServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "lvServices setOnItemClickListener: ");
                MService mService = list.get(position);
                BluetoothGattService service = mService.getService();

//                System.out.println("service---------------->"+service.getUuid().toString());

                myApplication.setCharacteristics(service.getCharacteristics());

                Intent intent = new Intent(ServicesActivity.this, CharacteristicsActivity.class);
                UUID serviceUuid = service.getUuid();
                Log.d(TAG, "service---------------->uuid:"+service.getUuid().toString());
//                Log.d(TAG, "serviceUuid: "+serviceUuid.toString());
                if (serviceUuid.toString().equals(GattAttributes.USR_SERVICE)) {
                    intent.putExtra("is_usr_service", true);

                    //这里为了方便暂时直接用Application serviceType 来标记当前的服务，应该是和上面的代码合并
                    MyApplication.serviceType = MyApplication.SERVICE_TYPE.TYPE_USR_DEBUG;
                }
                else if(service.getUuid().toString().equals(GattAttributes.BATTERY_SERVICE) ||
                        service.getUuid().toString().equals(GattAttributes.RGB_LED_SERVICE_CUSTOM)){
                    MyApplication.serviceType = MyApplication.SERVICE_TYPE.TYPE_NUMBER;
                }else {
                    MyApplication.serviceType = MyApplication.SERVICE_TYPE.TYPE_OTHER;
                }

                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }


    private void initUI() {
        rlTop= (RelativeLayout) findViewById(R.id.rl_top);
        filterView=  findViewById(R.id.view_filter);
        viewShadow=  findViewById(R.id.view_shadow);
        lvServices= (ListView) findViewById(R.id.lv_services);
        ivBle= (ImageView) findViewById(R.id.iv_ble);
        tvServiceName= (TextView) findViewById(R.id.tv_service_name);
        tvServiceMac= (TextView) findViewById(R.id.tv_service_mac);
        tvServiceCount= (TextView) findViewById(R.id.tv_service_count);
    }

    private void itemClick(){
        Log.d(TAG, "itemClick: ");
        int position=0;
        boolean isContains=false;
        for(int i=0;i<list.size();i++){
            MService mService = list.get(i);
            BluetoothGattService service = mService.getService();

            UUID serviceUuid = service.getUuid();
            if (serviceUuid.toString().equals(GattAttributes.USR_SERVICE)) {
                position=i;
                isContains=true;
                break;
            }
        }

        if (!isContains){
            Toast.makeText(context, "There is not USR_SERVICE,please try another device!", Toast.LENGTH_SHORT).show();
            return;
        }

        MService mService = list.get(position);
        BluetoothGattService service = mService.getService();
        myApplication.setCharacteristics(service.getCharacteristics());

        MyApplication.serviceType = MyApplication.SERVICE_TYPE.TYPE_USR_DEBUG;

        Intent intent = new Intent(context, CharacteristicsActivity.class);
        intent.putExtra("is_usr_service", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        context.startActivity(intent);

    }

   /* //mohuaiyuan 201707
    private void startAnimation() {
        rlTop.setAlpha(0.0f);
        rlTop.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ObjectAnimator animator1 = ObjectAnimator.ofInt(rlTop,"backgroundColor",
                Color.parseColor("#0277bd"), Color.parseColor("#009688"));
        animator1.setDuration(700);
        animator1.setStartDelay(100);
        animator1.setEvaluator(new ArgbEvaluator());
        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                viewShadow.setVisibility(View.VISIBLE);
                rlTop.setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });

        //mohuaiyuan 201707
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
//            toolbar.setAlpha(0.0f);
//            toolbar.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//            ObjectAnimator animator0 = ObjectAnimator.ofInt(toolbar,"backgroundColor",
//                    Color.parseColor("#0277bd"), Color.parseColor("#009688"));
//            animator0.setDuration(700);
//            animator0.setStartDelay(100);
//            animator0.setEvaluator(new ArgbEvaluator());
//            animator0.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    toolbar.setLayerType(View.LAYER_TYPE_NONE, null);
//                }
//            });
//            animator0.start();
//            AnimateUtils.alpha(toolbar, 1.0f, 400, 0);
//        }


        animator1.start();
        AnimateUtils.alpha(rlTop,1.0f,400,0);
        startIntroAnimator();
    }*/

   /* private void startIntroAnimator(){
        ivBle.setVisibility(View.VISIBLE);
        tvServiceName.setVisibility(View.VISIBLE);
        tvServiceMac.setVisibility(View.VISIBLE);
        tvServiceCount.setVisibility(View.VISIBLE);
        ivBle.setTranslationX(-Utils.dpToPx(100));
        ivBle.setRotation(-360f);
        ivBle.setAlpha(0f);
        tvServiceName.setTranslationX(Utils.dpToPx(300));
        tvServiceName.setAlpha(0f);
        tvServiceMac.setTranslationX(Utils.dpToPx(300));
        tvServiceMac.setAlpha(0f);
        tvServiceCount.setTranslationX(Utils.dpToPx(300));
        tvServiceCount.setAlpha(0f);

        lvServices.setTranslationY(Utils.dpToPx(300));


        AnimateUtils.translationX(ivBle,0,400,400);
        AnimateUtils.rotation(ivBle,0f,400,400,null);
        AnimateUtils.alpha(ivBle,1.0f,400,400);
        AnimateUtils.translationX(tvServiceName,0,400,400);
        AnimateUtils.alpha(tvServiceName,1.0f,400,400);
        AnimateUtils.translationX(tvServiceMac,0,400,500);
        AnimateUtils.alpha(tvServiceMac,1.0f,400,500);
        AnimateUtils.translationX(tvServiceCount,0,400,600);
        AnimateUtils.alpha(tvServiceCount,1.0f,400,600);

        AnimateUtils.alpha(lvServices,1.0f,200,400);
        AnimateUtils.translationY(lvServices,0,400,400);
    }
*/

}
