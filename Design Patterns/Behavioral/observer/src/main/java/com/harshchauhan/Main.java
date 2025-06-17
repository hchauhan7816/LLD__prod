package com.harshchauhan;

import com.harshchauhan.classes.YoutubeChannel;
import com.harshchauhan.classes.subscriber.EmailSubscriber;
import com.harshchauhan.classes.subscriber.MobileNotificationSubscriber;

public class Main {
    public static void main(String[] args) {
        EmailSubscriber emailSubscriber = new EmailSubscriber("harsh@gmail.com");
        MobileNotificationSubscriber mobileNotificationSubscriber = new MobileNotificationSubscriber("111-22-3333");

        YoutubeChannel harshYoutubeChannel = new YoutubeChannel();

        harshYoutubeChannel.addSubscriber(emailSubscriber);

        harshYoutubeChannel.uploadVideo("first");

        harshYoutubeChannel.addSubscriber(mobileNotificationSubscriber);

        harshYoutubeChannel.uploadVideo("second");

        harshYoutubeChannel.removeSubscriber(emailSubscriber);

        harshYoutubeChannel.uploadVideo("third");

        harshYoutubeChannel.removeSubscriber(mobileNotificationSubscriber);

        harshYoutubeChannel.uploadVideo("fourth");
    }
}