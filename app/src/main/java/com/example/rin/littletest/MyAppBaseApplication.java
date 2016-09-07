package com.example.rin.littletest;

/**
 * Created by Rin on 9/5/2016.
 */
public class MyAppBaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Launch Notificare system
        Notificare.shared().launch(this);
        // Set our own intent receiver
        Notificare.shared().setIntentReceiver(AppReceiver.class);
        // Uncomment this to keep the notification in the drawer after opening
        //Notificare.shared().setAutoCancel(false);

        // Uncomment to use a different icon for notifications
        //Notificare.shared().setSmallIcon(R.drawable.small_icon);

        // Uncomment to use a different accent color for notifications
        //Notificare.shared().setNotificationAccentColor(ContextCompat.getColor(this, R.color.notification_accent_color));

        // Uncommment this to show a notification light by default, even if it isn't sent with the incoming notification
        //Notificare.shared().setDefaultLightsColor("green");
        //Notificare.shared().setDefaultLightsOn(500);
        //Notificare.shared().setDefaultLightsOff(1500);
    }
}