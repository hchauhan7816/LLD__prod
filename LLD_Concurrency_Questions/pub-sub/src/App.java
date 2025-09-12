import entities.Message;
import entities.Topic;
import kafka.KafkaController;
import subscriber.concrete.SimpleSubscriber;
import publisher.concrete.SimplerPublisher;

public class App {
    public static void main(String[] args) throws Exception {
        KafkaController kafkaController = new KafkaController();

        System.out.println("🚀 Starting Pub-Sub System Demo");
        System.out.println("==================================================");
        
        // Create topics.
        System.out.println("\n📋 Creating Topics:");
        Topic topic1 = kafkaController.createTopic("Topic1");
        Topic topic2 = kafkaController.createTopic("Topic2");

        // Create subscribers.
        System.out.println("\n👥 Creating Subscribers:");
        SimpleSubscriber subscriber1 = new SimpleSubscriber("Subscriber1");
        SimpleSubscriber subscriber2 = new SimpleSubscriber("Subscriber2");
        SimpleSubscriber subscriber3 = new SimpleSubscriber("Subscriber3");

        // Subscribe: subscriber1 subscribes to both topics,
        // subscriber2 subscribes to topic1, and subscriber3 subscribes to topic2.
        System.out.println("\n🔗 Setting up Subscriptions:");
        kafkaController.subscribe(subscriber1, topic1.getId());
        kafkaController.subscribe(subscriber1, topic2.getId());
        kafkaController.subscribe(subscriber2, topic1.getId());
        kafkaController.subscribe(subscriber3, topic2.getId());

        // Create publishers.
        System.out.println("\n📢 Creating Publishers:");
        SimplerPublisher publisher1 = new SimplerPublisher("Publisher1", kafkaController);
        SimplerPublisher publisher2 = new SimplerPublisher("Publisher2", kafkaController);

        // Publish some messages.
        System.out.println("\n📤 Publishing Messages (Round 1):");
        publisher1.publish(topic1.getId(), new Message("Message m1"));
        publisher1.publish(topic1.getId(), new Message("Message m2"));
        publisher2.publish(topic2.getId(), new Message("Message m3"));

        // Allow time for subscribers to process messages.
        System.out.println("\n⏳ Waiting for message processing...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n📤 Publishing Messages (Round 2):");
        publisher2.publish(topic2.getId(), new Message("Message m4"));
        publisher1.publish(topic1.getId(), new Message("Message m5"));

        // Reset offset for subscriber1 on topic1 (for example, to re-process messages).
        System.out.println("\n🔄 Demonstrating Offset Reset:");
        kafkaController.resetOffset(subscriber1, topic1.getId(), 0);

        // Allow some time before shutting down.
        System.out.println("\n⏳ Final processing time...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n🛑 Shutting down Pub-Sub System");
        kafkaController.shutdown();
    }
}
