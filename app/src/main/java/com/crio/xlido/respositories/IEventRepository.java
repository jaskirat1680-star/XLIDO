package com.crio.xlido.respositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Event;

public interface IEventRepository {
    Event save(Event entity);
    void deleteEvent(Long EventId);
    Optional<Event> getById(Long id);
    List<Event> getAllEvents();
    
}
