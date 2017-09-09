package us.jsand.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreadRepository {
    private Map<Long, Thread> threads;
    private Long sequence;

    public ThreadRepository() {
        this.threads = new HashMap<>();
        this.sequence = 0L;
    }

    public Thread save(Thread thread) {
        thread.setId(sequence++);
        threads.put(thread.getId(), thread);
        return thread;
    }

    public Thread findOne(Long id) {
        return threads.get(id);
    }

    public Iterable<Thread> findAll() {
        return threads.values();
    }

    public Long count() {
        return (long) threads.size();
    }

    public void delete(Long id) {
        threads.remove(id);
    }

    public boolean exists(Long id) {
        return threads.containsKey(id);
    }
}
