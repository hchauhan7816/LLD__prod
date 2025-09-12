package topicSubscriber;

import entities.Message;
import entities.Topic;
import subscriber.ISubscriber;

public class TopicSubscriberController implements Runnable {
    private final TopicSubscriber topicSubscriber;

    public TopicSubscriberController(TopicSubscriber topicSubscriber) {
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        Topic topic = topicSubscriber.getTopic();
        ISubscriber subscriber = topicSubscriber.getSubscriber();

        while (true) {
            Message messageToProcess = null;

            synchronized (topicSubscriber) {
                while (topicSubscriber.getOffset().get() >= topic.getMessageCount()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                int currentOffset = topicSubscriber.getOffset().getAndIncrement();
                messageToProcess = topic.getMessage(currentOffset);
            }

            try {
                subscriber.onMessage(messageToProcess);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
