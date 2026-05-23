package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.EventService;

public class DeleteQuestionCommand implements ICommand{
    private EventService eventService;

    public DeleteQuestionCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long questionId = Long.parseLong(tokens.get(1));
        Long userId = Long.parseLong(tokens.get(2));
        eventService.deleteQuestion(questionId, userId);
        System.out.println("QUESTION_DELETED "+questionId);
    }
    
    
}
