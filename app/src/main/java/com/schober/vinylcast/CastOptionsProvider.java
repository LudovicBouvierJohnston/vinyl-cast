package com.schober.vinylcast;

import android.content.Context;

import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationOptions;

import java.util.ArrayList;
import java.util.List;

public class CastOptionsProvider implements OptionsProvider {
    @Override
    public CastOptions getCastOptions(Context appContext) {

        List<String> buttonActions = new ArrayList<>();
        buttonActions.add(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
        buttonActions.add(MediaIntentReceiver.ACTION_STOP_CASTING);
        // Showing "play/pause" and "stop casting" in the compat view of the notification.
        int[] compatButtonActionsIndicies = new int[]{ 0, 1 };
        // Builds a notification with the above actions.
        // Tapping on the notification opens an Activity with class VideoBrowserActivity.
        NotificationOptions notificationOptions = new NotificationOptions.Builder()
                .setActions(buttonActions, compatButtonActionsIndicies)
                .setTargetActivityClassName(MainActivity.class.getName())
                .build();

        CastMediaOptions mediaOptions = new CastMediaOptions.Builder()
                .setNotificationOptions(notificationOptions)
                .build();

        CastOptions castOptions = new CastOptions.Builder()
                .setReceiverApplicationId(appContext.getString(R.string.cast_app_id))
                .setCastMediaOptions(mediaOptions)
                .build();
        return castOptions;
    }
    @Override
    public List<SessionProvider> getAdditionalSessionProviders(Context context) {
        return null;
    }
}