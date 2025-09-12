package publisher.concrete;

import entities.Message;
import kafka.KafkaController;
import publisher.IPublisher;

public class SimplerPublisher implements IPublisher {
    private String id;
    private KafkaController kafkaController;

    public SimplerPublisher(String id, KafkaController kafkaController) {
        this.id = id;
        this.kafkaController = kafkaController;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void publish(String topicId, Message message) {
        kafkaController.publish(this, topicId, message);
        System.out.println("📤 [" + id + "] Published: " + message.getContent() + " → Topic " + topicId);
    }
}
