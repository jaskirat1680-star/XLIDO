package com.crio.xlido.commands;

import java.util.List;
import com.crio.xlido.services.QuestionService;

public class UpVoteQuestionCommand implements ICommand{
    private final QuestionService questionService;

    
    public UpVoteQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public void invoke(List<String> tokens) {
        Long userId = Long.parseLong(tokens.get(2));
        Long questionId = Long.parseLong(tokens.get(1));
        questionService.addUpVote(userId, questionId);
        System.out.println("QUESTION_UPVOTED "+questionId);
        
    }
    
}
