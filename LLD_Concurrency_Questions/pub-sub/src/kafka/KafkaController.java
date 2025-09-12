package kafka;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import entities.Message;
import entities.Topic;
import publisher.IPublisher;
import subscriber.ISubscriber;
import topicSubscriber.TopicSubscriber;
import topicSubscriber.TopicSubscriberController;

public class KafkaController {
    // private final Map<String, List<TopicPublisher>> topicPublishers;

    private final Map<String, Topic> topics;
    private final Map<String, List<TopicSubscriber>> topicSubscribers;
    private final ExecutorService subscriberExecutor;
    private final AtomicInteger topicIdCounter;

    public KafkaController() {
        topics = new ConcurrentHashMap<>();
        topicSubscribers = new ConcurrentHashMap<>();
        subscriberExecutor = Executors.newCachedThreadPool();
        topicIdCounter = new AtomicInteger(0);
    }

    public Topic createTopic(String topicName) {
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicName, topicId);
        topics.put(topicId, topic);
        topicSubscribers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("✅ Topic '" + topicName + "' created with ID: " + topicId);
        return topic;
    }

    public void publish(IPublisher publisher, String topicId, Message message) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic not found");
        }
        topic.addMessage(message);
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        for (TopicSubscriber topicSubscriber : subscribers) {
            synchronized (topicSubscriber) {
                topicSubscriber.notify();
            }
        }
        // Removed redundant message - publisher already logs this
    }

    public void subscribe(ISubscriber subscriber, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic not found");
        }
        TopicSubscriber topicSubscriber = new TopicSubscriber(subscriber, topic);
        topicSubscribers.get(topicId).add(topicSubscriber);
        subscriberExecutor.submit(new TopicSubscriberController(topicSubscriber));
        System.out.println("📡 Subscriber '" + subscriber.getId() + "' subscribed to topic ID: " + topicId);
    }

    public void resetOffset(ISubscriber subscriber, String topicId, Integer newOffset) {
        List<TopicSubscriber> subscribers = topicSubscribers.get(topicId);
        if (subscribers == null) {
            throw new IllegalArgumentException("No subscribers found for topic: " + topicId);
        }
        for (TopicSubscriber topicSubscriber : subscribers) {
            if (topicSubscriber.getSubscriber().getId().equals(subscriber.getId())) {
                topicSubscriber.getOffset().set(newOffset);
                synchronized (topicSubscriber) {
                    topicSubscriber.notify();
                }
                System.out.println("🔄 Offset reset to " + newOffset + " for subscriber '" + subscriber.getId() + "' on topic ID: " + topicId);
                break;
            }
        }
    }

    public void shutdown() {
        subscriberExecutor.shutdown();
        try {
            if (!subscriberExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                subscriberExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            subscriberExecutor.shutdownNow();
        }
    }
}
