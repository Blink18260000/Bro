package com.simpledev.eric.bro;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainBro extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.simpledev.eric.bro.MESSAGE";

    Button broButton;
    //Button broDawgButton;
    EditText broPhoneNumber;
    String curBroVers;
    int broVers;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bro);

        broButton = (Button) findViewById(R.id.broButton);
        //broDawgButton = (Button) findViewById(R.id.broDawgButton);
        broPhoneNumber = (EditText) findViewById(R.id.edit_message);

        /**
        broDawgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = broPhoneNumber.getText().toString();
                if (phoneNo.length() == 10)
                    sendSMS(phoneNo, 1);
            }
        });
        */

        broButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = broPhoneNumber.getText().toString();
                if (phoneNo.length() == 10)
                    broVers = rand.nextInt(9);
                    if (broVers == 9) {
                        sendSMS(phoneNo, 1);
                    } else {
                        sendSMS(phoneNo, 0);
                    }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_bro, menu);
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

    private void sendSMS(String broNumber, int broVers)
    {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainBro.class), 0);
        SmsManager sms = SmsManager.getDefault();
        if (broVers == 0) {
            curBroVers = "Bro";
        } else if (broVers == 1) {
            curBroVers = "Bro Dawg";
        } else {
            curBroVers = "Invalid Bro";
        }
        sms.sendTextMessage(broNumber, null, curBroVers, pi, null);
    }

}
