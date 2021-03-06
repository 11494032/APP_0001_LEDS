package com.example.lenovo.app_0001_leds;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.ILedService;
import android.os.ServiceManager;


public class MainActivity extends Activity {

    private Button buttononoff;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private boolean on = false;
    private  int i = 0;

    private ILedService iLedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iLedService = ILedService.Stub.asInterface(ServiceManager.getService("led"));         ;

        buttononoff = (Button)findViewById(R.id.button);

        checkBox1 = (CheckBox)findViewById(R.id.led1);
        checkBox2 = (CheckBox)findViewById(R.id.led2);
        checkBox3 = (CheckBox)findViewById(R.id.led3);
        checkBox4 = (CheckBox)findViewById(R.id.led4);

        buttononoff.setOnClickListener( new View.OnClickListener()
        {


            @Override
            public void onClick(View view) {
                on = !on;
                if( on )
                {
                    buttononoff.setText("all on");
                    checkBox1.setChecked(true);
                    checkBox1.setChecked(true);
                    checkBox1.setChecked(true);
                    checkBox1.setChecked(true);
                    try {
                        for( i = 0; i<4; i++ )
                        {
                            iLedService.ledCtrl(i, 1);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    buttononoff.setText("all off");
                    checkBox1.setChecked(false);
                    checkBox1.setChecked(false);
                    checkBox1.setChecked(false);
                    checkBox1.setChecked(false);
                    try {
                        for( i = 0; i<4; i++ )
                        {
                            iLedService.ledCtrl(i, 0);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        try {
            switch(view.getId()) {
                case R.id.led1:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "led1 on",
                                Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0, 1);
                        // Put some meat on the sandwich
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "led2 off",
                                Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0, 0);
                        // Remove the meat
                    }
                    break;

                case R.id.led2:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "led2 on",
                                Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1, 1);
                        // Put some meat on the sandwich
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "led2 off",
                                Toast.LENGTH_SHORT).show();

                        iLedService.ledCtrl(1, 0);
                        // Remove the meat
                    }
                    break;

                case R.id.led3:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "led3 on",
                                Toast.LENGTH_SHORT).show();
                        // Put some meat on the sandwich
                        iLedService.ledCtrl(2, 1);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "led3 off",
                                Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2, 0);
                        // Remove the meat
                    }
                    break;

                case R.id.led4:
                    if (checked) {
                        Toast.makeText(getApplicationContext(), "led4 on",
                                Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3, 0);
                        // Put some meat on the sandwich
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "led4 off",
                                Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3, 1);
                        // Remove the meat
                    }
                    break;

                // TODO: Veggie sandwich
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
