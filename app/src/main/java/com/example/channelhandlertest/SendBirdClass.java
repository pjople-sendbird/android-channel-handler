package com.example.channelhandlertest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.BaseMessageParams;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.GroupChannelListQuery;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.OpenChannelListQuery;
import com.sendbird.android.OpenChannelParams;
import com.sendbird.android.ReactionEvent;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.SendBirdPushHelper;
import com.sendbird.android.User;
import com.sendbird.android.UserMessage;
import com.sendbird.android.UserMessageParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SendBirdClass {

    private Context mContext;

    private static final String TAG = "SendBird";
    public static final String APP_ID = "D1CB1742-A4A3-44B9-9E7F-126D14BAB34B";
    public static final String USER_ID = "test2";

    private SBListener mListener;

    public SendBirdClass(Context context, SBListener listener) {
        mContext = context;
        mListener = listener;
        initSendBird();
    }

    private void initSendBird() {
        com.sendbird.android.SendBird.init(APP_ID, mContext);
    }

    public void connectToSendBird() {
        com.sendbird.android.SendBird.connect(USER_ID, new com.sendbird.android.SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {    // Error.
                    Toast.makeText(mContext, "Error connecting", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    mListener.onSendBirdConnected(false);
                } else {
                    setChannelHandlers();
                    mListener.onSendBirdConnected(true);
                }
            }
        });
    }

    private void setChannelHandlers() {
        com.sendbird.android.SendBird.addChannelHandler("123456", new com.sendbird.android.SendBird.ChannelHandler() {
            @Override
            public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
                mListener.onMessageReceived(baseChannel, baseMessage);
            }
            @Override
            public void onMessageUpdated(BaseChannel baseChannel, BaseMessage baseMessage) {
            }
            @Override
            public void onMessageDeleted(BaseChannel baseChannel, long messageId) {
            }
            @Override
            public void onMentionReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
            }
            @Override
            public void onReactionUpdated(BaseChannel baseChannel, ReactionEvent reactionEvent) {
            }
            @Override
            public void onChannelChanged(BaseChannel baseChannel) {
                Log.i(TAG, "CHANNEL CHANGED");
            }
            @Override
            public void onChannelDeleted(String channelUrl, BaseChannel.ChannelType channelType) {
            }
            @Override
            public void onChannelFrozen(BaseChannel baseChannel) {
            }
            @Override
            public void onChannelUnfrozen(BaseChannel baseChannel) {
            }
            @Override
            public void onMetaDataCreated(BaseChannel baseChannel, Map map) {
            }
            @Override
            public void onMetaDataUpdated(BaseChannel baseChannel, Map map) {
            }
            @Override
            public void onMetaDataDeleted(BaseChannel baseChannel, List<String> keys) {
            }
            @Override
            public void onChannelHidden(GroupChannel groupChannel) {
            }
            @Override
            public void onUserReceivedInvitation(GroupChannel groupChannel, User inviter, List<User> invitees) {
            }
            @Override
            public void onUserDeclinedInvitation(GroupChannel groupChannel, User inviter, User invitee) {
            }
            @Override
            public void onUserJoined(GroupChannel groupChannel, User user) {
            }
            @Override
            public void onUserLeft(GroupChannel groupChannel, User user) {
            }
            @Override
            public void onDeliveryReceiptUpdated(GroupChannel groupChannel) {
            }
            @Override
            public void onReadReceiptUpdated(GroupChannel groupChannel) {
            }
            @Override
            public void onTypingStatusUpdated(GroupChannel groupChannel) {
            }
            @Override
            public void onUserEntered(OpenChannel openChannel, User user) {
                Log.i(TAG, "User entered");
            }
            @Override
            public void onUserExited(OpenChannel openChannel, User user) {
                Log.i(TAG, "User exited");
            }
            @Override
            public void onUserMuted(BaseChannel baseChannel, User user) {
            }
            @Override
            public void onUserUnmuted(BaseChannel baseChannel, User user) {
            }
            @Override
            public void onUserBanned(BaseChannel baseChannel, User user) {
            }
            @Override
            public void onUserUnbanned(BaseChannel baseChannel, User user) {
            }
        });
        Log.i(TAG, "Channel listener set");
    }
}
