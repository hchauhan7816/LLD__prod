package entities;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String id;
    private final String name;

    List<Message> messages;

    public Topic(final String name, final String id) {
        this.name = name;
        this.id = id;
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public synchronized void addMessage(Message message) {
        messages.add(message);
    }

    public synchronized Message getMessage(int index) {
        return messages.get(index);
    }

    public synchronized int getMessageCount() {
        return messages.size();
    }
    
}
