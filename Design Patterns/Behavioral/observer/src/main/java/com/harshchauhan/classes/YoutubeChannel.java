package com.harshchauhan.classes;

import java.util.ArrayList;
import java.util.List;

import com.harshchauhan.classes.subscriber.Subscriber;

public class YoutubeChannel implements YoutubeChannelInterface {

    List<Subscriber> subscribers = new ArrayList<>();
    String videoId;

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoId) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(videoId);
        }
    }

    public void uploadVideo(String videoId) {
        this.videoId = videoId;
        notifySubscribers(videoId);
    }
}
