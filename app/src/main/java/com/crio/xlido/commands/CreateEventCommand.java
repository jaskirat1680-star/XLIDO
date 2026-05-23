package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.entities.Event;
import com.crio.xlido.services.EventService;

public class CreateEventCommand implements ICommand{
    private EventService eventService;
    
    public CreateEventCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String title = tokens.get(1);
        Long organizerId = Long.parseLong(tokens.get(2));
        Event event = eventService.createEvent(title, organizerId);
        System.out.println("Event ID: "+event.getId());
    }
    
}
