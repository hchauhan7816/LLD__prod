import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedServer {

    private String hostname;

    ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<String, Boolean>();

    AtomicInteger counter = new AtomicInteger(0);

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    private HTMLParser htmlParser;

    public void start(HTMLParser htmlParser) {

        Runnable task = () -> {

        };

        String startUrl = "https://news.yahoo.com/news/topics";

        String hostName = startUrl.split("/")[2];

        map.put(hostName, true);
        counter.addAndGet(1);

        
    }
}
