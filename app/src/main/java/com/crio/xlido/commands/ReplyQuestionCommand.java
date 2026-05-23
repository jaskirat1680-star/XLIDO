package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.QuestionService;

public class ReplyQuestionCommand implements ICommand{
    private QuestionService questionService;

    public ReplyQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }
    
    @Override
    public void invoke(List<String> tokens) {
        Long questionId = Long.parseLong(tokens.get(2));
        Long userId = Long.parseLong(tokens.get(3));
        String content = tokens.get(1);
        questionService.addReply(questionId, userId, content);
        System.out.println("REPLY_ADDED");
        
    }

}
