package epam.task3;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;

public class MessageBus {

    private final Deque<Message> queue = new LinkedList<>();
    
    public synchronized void postMessage(Message message) {
        queue.addLast(message);
        notifyAll();
    }
    
    public synchronized Optional<Message> consumeMessage(String topic) {
        for (Iterator<Message> it = queue.iterator(); it.hasNext(); ) {
            Message message = it.next();
            if (message.getTopic().equals(topic)) {
                it.remove();
                return Optional.of(message);
            }
        }
        return Optional.empty();
    }
}
