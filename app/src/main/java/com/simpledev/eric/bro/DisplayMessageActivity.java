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
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    String message;
    SmsManager sms;
    android.os.Handler customHandler;
    TextView broingText;
    Button stopButton;
    //Boolean stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        message = intent.getStringExtra(MainBro.EXTRA_MESSAGE);

        setContentView(R.layout.activity_display_message);
        broingText = (TextView) findViewById(R.id.numberBroed);
        broingText.setText("The number " + message + " is currently being broed.");

        /**
        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("The number " + message + " is currently being broed.");

        // Set the text view as the activity layout
        setContentView(textView);
        */

        //stop = false;

        stopButton = (Button) findViewById(R.id.stop_button);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customHandler.removeCallbacks(broThem);
                goHome();
            }
        });

        customHandler = new android.os.Handler();
        customHandler.postDelayed(broThem, 0);
        sms = SmsManager.getDefault();
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

    private Runnable broThem = new Runnable()
    {
        public void run()
        {
            sendSMS(message);
            customHandler.postDelayed(this, 1000);
        }
    };

    private void sendSMS(String broNumber)
    {
        sms.sendTextMessage(broNumber, null, "Bro...", null, null);
    }

    private void goHome() {
        Intent intent = new Intent(this, MainBro.class);
        startActivity(intent);
    }
}
