package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.EventService;

public class DeleteEventCommand implements ICommand{

    private EventService eventService;
    public DeleteEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long eventId = Long.parseLong(tokens.get(1));
        Long userId = Long.parseLong(tokens.get(2));
        eventService.deleteEvent(userId, eventId);
        System.out.println("EVENT_DELETED "+eventId);
    }
    
}
