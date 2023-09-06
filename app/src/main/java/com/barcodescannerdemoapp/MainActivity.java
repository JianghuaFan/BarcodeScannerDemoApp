package com.barcodescannerdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.rokid.glass.instruct.InstructLifeManager;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;
public class MainActivity extends AppCompatActivity {
    private InstructLifeManager mLifeManager;
    public static int REQUEST_ENABLE_BT = 123;
    private static final int REQUEST_PERMISSION_CODE = 123;
    private Button btnStart;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        btnStart = findViewById(R.id.btnStart);
        // If the button is clicked, the method setOnClickListener would be invoked to start the Scan activity for scanning bar code,
        // If the button is red by people, the method configInstruct would be invoked.

        // Set click listener for scan button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTON", "User tapped the Start Scan Button");
                startScan();
            }
        });
        configInstruct();
    }
    public void configInstruct(){
        mLifeManager = new InstructLifeManager(this, getLifecycle(), mInstructLifeListener);
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.btnStart)
                        .addEntityKey(new EntityKey(EntityKey.Language.ja,"スキャン"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"掃描 觸發");
                                startScan();
                            }
                        })
        );
        Log.d("BUTTON", "User read the Start Scan Button");
    }

    private InstructLifeManager.IInstructLifeListener mInstructLifeListener = new InstructLifeManager.IInstructLifeListener() {
        @Override
        public boolean onInterceptCommand(String command) {
            if ("需要拦截的指令".equals(command)) {
                return true;
            }
            return false;
        }

        @Override
        public void onTipsUiReady() {
            Log.d("AudioAi", "onTipsUiReady Call ");
        }

        @Override
        public void onHelpLayerShow(boolean show) {

        }
    };
    private void startScan() {
        // Get the BluetoothAdapter and set up bluetooth
        Log.d("startScan", "startScan");
       // start receiving data from barcode scanner through bluetooth
        Intent intent = new Intent(MainActivity.this, com.barcodescannerdemoapp.EditText.class);
        startActivity(intent);
    }

}