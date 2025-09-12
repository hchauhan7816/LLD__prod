package subscriber.concrete;

import entities.Message;
import subscriber.ISubscriber;

public class SimpleSubscriber implements ISubscriber {
    private String id;

    public SimpleSubscriber(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("📨 [" + id + "] Received: " + message.getContent());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
