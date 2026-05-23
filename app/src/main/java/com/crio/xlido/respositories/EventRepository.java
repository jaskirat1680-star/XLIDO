package com.crio.xlido.respositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Event;


public class EventRepository implements IEventRepository{
    private final Map<Long, Event> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong();
    @Override
    public Event save(Event entity) {
        Event event = new Event(idCounter.incrementAndGet(), entity);
        storage.putIfAbsent(event.getId(), event);
        return event;
    }

    @Override
    public void deleteEvent(Long EventId) {
        storage.remove(EventId);

    }

    @Override
    public Optional<Event> getById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(storage.values());
    }    
    
}
