package topicPublisher;

import entities.Message;
import entities.Topic;
import kafka.KafkaController;
import publisher.IPublisher;

public class TopicPublisherController {
    private final Topic topic;
    private final IPublisher publisher;

    public TopicPublisherController(Topic topic, IPublisher publisher) {
        this.topic = topic;
        this.publisher = publisher;
    }

    public synchronized void publish(Message message, KafkaController kafkaController) {
        kafkaController.publish(publisher, topic.getId(), message);
        System.out.println("Published message: " + message.getContent() + " to topic: " + topic.getId());
    }
}
