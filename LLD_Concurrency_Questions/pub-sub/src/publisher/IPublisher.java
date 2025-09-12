package publisher;

import entities.Message;

public interface IPublisher {
    String getId();

    void publish(String topicId, Message message);
}
