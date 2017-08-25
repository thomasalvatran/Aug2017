//https://github.com/akexorcist
//https://github.com/GayratRakhimov/LabBluetooth/blob/master/app/src/main/res/values/dimens.xml
//https://www.youtube.com/watch?v=DtiAzGnIwS0&t=58s

package bluetooth.tv.example.com.bluetooth;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;


//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
public class MainActivity extends AppCompatActivity {

    final String ON = "1";
    final String OFF = "0";

    BluetoothSPP bluetooth;

    Button connect;
    ImageButton on, ledon;
    ImageButton off, ledoff;
    private String TAG = "MainActivity Configuration SCREEN";
    private String TAG1 = "Main Bluetooth Send";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //system control bar (status bar) then toolbar after
        setContentView(R.layout.activity_main);


//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
//        actionBar.setIcon(R.drawable.ic_launcher);
        ActionBar ab = getSupportActionBar();  //icon on toolbar
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setLogo(R.mipmap.ic_launcher);
        ab.setHomeAsUpIndicator(R.mipmap.ic_launcher);

//        configuraImageButton();

        bluetooth = new BluetoothSPP(this);

        connect = (Button) findViewById(R.id.connect);
        on = (ImageButton) findViewById(R.id.on);
        off = (ImageButton) findViewById(R.id.off);
        ledon = (ImageButton) findViewById(R.id.ledon);
        ledoff = (ImageButton) findViewById(R.id.ledoff);

        if (!bluetooth.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(), "Bluetooth is not available", Toast.LENGTH_SHORT).show();
            finish();
        }

        bluetooth.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                connect.setText("Connected to " + name);
            }

            public void onDeviceDisconnected() {
                connect.setText("Connection lost");
            }

            public void onDeviceConnectionFailed() {
                connect.setText("Unable to connect");
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bluetooth.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Application send a '1' to target via Bluetooth";
                bluetoothMsg(msg);
                bluetooth.send(ON, true);
                Log.e(TAG1, "SEND_ON");

//                if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED)
//                    Toast.makeText(MainActivity.this, "Application send a '1' to target via Bluetooth", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(MainActivity.this, "No Bluetooth connection, please connect Bluetooth first", Toast.LENGTH_LONG).show();
//                Toast toast = Toast.makeText(MainActivity.this, "Application send a '1' to target via Bluetooth", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity. TOP, 0 , 0);
//                toast.show();
//                if(on.getVisibility() == View.INVISIBLE)
                on.setVisibility(View.INVISIBLE); //ON and OFF button
                off.setVisibility(View.VISIBLE); //ON and OFF button
//                ImageButton imgBnt = (ImageButton) findViewById(R.id.ledoff);
                ledon.setVisibility(View.VISIBLE);
                ledoff.setVisibility(View.INVISIBLE);
//                ImageView img = (ImageView)findViewById(R.id.ledon);
//                img.setVisibility(View.INVISIBLE);
//               img.setImageResource(R.drawable.ledon);
                //img.setBackgroundResource(R.drawable.ledon);
//                on.setBackgroundResource(R.drawable.ledon);
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Application send a '0' to target via Bluetooth";
                if (bluetoothMsg(msg))
                    return;
                bluetooth.send(OFF, true);
                Log.e(TAG1, "SEND_OFF");

//                if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED)
//                    Toast.makeText(MainActivity.this, "Application send a '0' to target via Bluetooth", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(MainActivity.this, "No Bluetooth connection, please connect Bluetooth first", Toast.LENGTH_LONG).show();
//                Toast toast = Toast.makeText(MainActivity.this, "Application send a '0' to target via Bluetooth", Toast.LENGTH_LONG);
//                toast.setGravity(Gravity. TOP, 0 , 0);
//                toast.show();

                off.setVisibility(View.INVISIBLE);
                on.setVisibility(View.VISIBLE);
//                ImageButton imgBnt = (ImageButton) findViewById(R.id.ledon);
                ledoff.setVisibility(View.VISIBLE);
                ledon.setVisibility(View.INVISIBLE);


                //on.setBackgroundResource(R.drawable.ledon);
//                ImageView img = (ImageView)findViewById(R.id.ledoff);
//                img.setVisibility(View.VISIBLE);
//                img.setImageResource(R.drawable.ledoff);
//                img.setBackgroundResource(R.drawable.ledoff);
//                off.setBackgroundResource(R.drawable.ledoff);
            }
        });

    }
//    private void configuraImageButton() {
//        ImageButton btn = (ImageButton) findViewById(R.id.LEDOFF);
//    }
    public boolean bluetoothMsg(String msg) {
        if (bluetooth.getServiceState() == BluetoothState.STATE_CONNECTED) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(MainActivity.this, "No Bluetooth connection, please connect Bluetooth first", Toast.LENGTH_LONG).show();
//            return;  //stop execute from mainloop and exit.
//            finish(); //exit the app complete
//            startActivity(new Intent(MainActivity.this, MainActivity.class)); //start app
//            Intent i = new Intent(MainActivity.this, MainActivity.class);
//            i.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(i);
            return true;
        }


    }
    public void onStart() {
        super.onStart();
        if (!bluetooth.isBluetoothEnabled()) {
            bluetooth.enable();
        } else {
            if (!bluetooth.isServiceAvailable()) {
                bluetooth.setupService();
                bluetooth.startService(BluetoothState.DEVICE_OTHER);
            }
        }
    }


    public void onDestroy() {
        super.onDestroy();
        bluetooth.stopService();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bluetooth.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bluetooth.setupService();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Toast.makeText(MainActivity.this, "Configuration state changes", Toast.LENGTH_LONG).show();
        LinearLayout ly = (LinearLayout) findViewById(R.id.activity_main);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e(TAG, "ORIENTATION_LANDSCAPE");
            //setContentView(R.layout.main);
//            setContentView(R.drawable.bg3);
            ly.setBackgroundResource(R.drawable.bg3);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e(TAG, "ORIENTATION_PORTRAIT");
            //setContentView(R.layout.activity_main);
//            setContentView(drawable.bg);
//            getWindow().setBackgroundDrawableResource(R.drawable.bg);
            ly.setBackgroundResource(R.drawable.bg);
        }
    }
}