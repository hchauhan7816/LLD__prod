package com.harshchauhan.classes;

import com.harshchauhan.classes.subscriber.Subscriber;

public interface YoutubeChannelInterface {
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscribers(String videoId);
}
