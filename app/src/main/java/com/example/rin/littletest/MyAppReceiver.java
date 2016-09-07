package com.example.rin.littletest;

/**
 * Created by Rin on 9/5/2016.
 */
public class MyAppReceiver extends DefaultIntentReceiver {

    @Override
    public void onReady() {
        // Enable this device for push notifications
        Notificare.shared().enableNotifications();

        if (Notificare.shared().isLocationUpdatesEnabled()) {
            // Enable location updates. These updates will only be registered after the device is registered to Notificare, so this call could also go into the onRegistrationFinished below
            Notificare.shared().enableLocationUpdates();
            //To enable beacons please call the following method
            Notificare.shared().enableBeacons();
        }

        //To enable in-app billing please invoke the following method
        Notificare.shared().enableBilling();
    }


    @Override
    public void onNotificationReceived(String alert, String notificationId, String inboxItemId,
                                       Bundle extras) {
        // Execute default behavior, i.e., put notification in drawer
        Log.d(TAG, " received with extra Notification" + extras.getString("mykey"));
        super.onNotificationReceived(alert, notificationId, inboxItemId, extras);
    }


    @Override
    public void onNotificationOpened(String alert, String notificationId, String inboxItemId,
                                     Bundle extras) {
        // Notification is in extras
        NotificareNotification notification = extras.getParcelable(Notificare.INTENT_EXTRA_NOTIFICATION);
        Log.d(TAG, "Notification was opened with type " + notification.getType());
        Log.d(TAG, "Notification was opened with extra " + notification.getExtra().get("mykey"));
        // By default, open the NotificationActivity and let it handle the Notification
        super.onNotificationOpened(alert, notificationId, inboxItemId, extras);
    }

    @Override
    public void onNotificationOpenRegistered(NotificareNotification notification, Boolean handled) {
        Log.d(TAG, "Notification with type " + notification.getType() + " was opened, handled by SDK: " + handled);
    }

    @Override
    public void onUrlClicked(Uri urlClicked, Bundle extras) {
        Log.i(TAG, "URL was clicked: " + urlClicked);
        NotificareNotification notification = extras.getParcelable(Notificare.INTENT_EXTRA_NOTIFICATION);
        if (notification != null) {
            Log.i(TAG, "URL was clicked for "" + notification.getMessage() + """);
        }
    }

    @Override
    public void onRegistrationFinished(String deviceId) {
        // Typically, you want to override this method
        // The default behaviour is to register device with the registration Id assigned by GCM and no userId
        // You could, for example, register as the logged in user and set a device tag after registration
        String currentUserId =...
        Notificare.shared().registerDevice(deviceId, currentUserId, new NotificareCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // We could also enable location updates here
                //	if (Notificare.shared().isLocationUpdatesEnabled()) {
                //		Notificare.shared().enableLocationUpdates();
                //		Notificare.shared().enableBeacons();
                //	}
                // After successful registration it is safe to set tags for this device
                ArrayList<String> tags = new ArrayList<String>();
                tags.add("test_device");
                Notificare.shared().addDeviceTags(tags, new NotificareCallback<Boolean>() {
                    @Override
                    public void onError(NotificareError error) {
                        // Got error
                    }

                    @Override
                    public void onSuccess(Boolean success) {
                        // Tags set successfully
                    }
                });
            }

            @Override
            public void onError(NotificareError error) {
                // Got error on device registration
            }
        });
    }

    @Override
    public void onActionReceived(Uri target) {
        // By default, we pass the target as data URI to your main activity in a launch intent
        super.onActionReceived(target);
    }
}