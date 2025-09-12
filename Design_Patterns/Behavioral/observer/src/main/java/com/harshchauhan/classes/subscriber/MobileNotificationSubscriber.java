package com.harshchauhan.classes.subscriber;

public class MobileNotificationSubscriber implements Subscriber {

    String mobileId;

    public MobileNotificationSubscriber(String mobileId) {
        this.mobileId = mobileId;
    }

    @Override
    public void update(String video) {
        System.out.println("Subscriber is notified for video :: " + video);
    }

}
