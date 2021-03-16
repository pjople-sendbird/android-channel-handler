package com.example.channelhandlertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;

public class MainActivity extends AppCompatActivity {

    LinearLayout mLayoutLog;
    SendBirdClass mSBClass;
    SBListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListener();
        initActivityComponents();
        connectToSB();
    }

    private void initActivityComponents() {
        mLayoutLog = (LinearLayout) findViewById(R.id.layoutLog);
    }

    private void setListener() {
        mListener = new SBListener() {
            @Override
            public void onSendBirdConnected(boolean success) {
                addLog("SendBird is connected.");
            }
            @Override
            public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
                addLog(baseChannel.getUrl() + ". New message: " + baseMessage.getMessage());
            }
        };
    }

    private void connectToSB() {
        mSBClass = new SendBirdClass(this, mListener);
        mSBClass.connectToSendBird();
    }

    private void addLog(String message) {
        TextView tv = new TextView(this);
        tv.setText(message);
        mLayoutLog.addView(tv);
    }


}