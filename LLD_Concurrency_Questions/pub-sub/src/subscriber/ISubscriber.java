package subscriber;

import entities.Message;

public interface ISubscriber {
    String getId();

    void onMessage(Message message);
}
