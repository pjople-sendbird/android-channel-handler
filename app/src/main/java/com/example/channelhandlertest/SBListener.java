package com.example.channelhandlertest;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.OpenChannel;

import java.util.List;

public interface SBListener {

    public void onSendBirdConnected(boolean success);

    public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage);

}
