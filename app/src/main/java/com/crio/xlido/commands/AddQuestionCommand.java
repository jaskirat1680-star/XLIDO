package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.entities.Question;
import com.crio.xlido.services.EventService;
import com.crio.xlido.services.QuestionService;

public class AddQuestionCommand implements ICommand{
    private EventService eventService;
    public AddQuestionCommand(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String content = tokens.get(1);
        Long userId =Long.parseLong(tokens.get(2));
        Long eventId = Long.parseLong(tokens.get(3));
        Question ques = eventService.addQuestion(eventId, userId, content);
        System.out.println("Question ID: "+ques.getId());
    }
    
}
