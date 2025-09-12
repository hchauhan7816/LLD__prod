package topicSubscriber;

import java.util.concurrent.atomic.AtomicInteger;

import entities.Topic;
import subscriber.ISubscriber;

public class TopicSubscriber {
    private Topic topic;
    private ISubscriber subscriber;
    private AtomicInteger offset;

    public TopicSubscriber(ISubscriber subscriber, Topic topic) {
        this.subscriber = subscriber;
        this.topic = topic;
        this.offset = new AtomicInteger(0);
    }

    public Topic getTopic() {
        return topic;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }
    
    public AtomicInteger getOffset() {
        return offset;
    }
}
