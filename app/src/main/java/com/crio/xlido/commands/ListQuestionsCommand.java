package com.crio.xlido.commands;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.Reply;
import com.crio.xlido.services.QuestionService;

public class ListQuestionsCommand implements ICommand{
    private QuestionService questionService;
    
    public ListQuestionsCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long eventId = Long.parseLong(tokens.get(1));
        String sortingPreferance = tokens.get(2);
        List<Question>ans = questionService.listQuesitons(eventId, sortingPreferance);
        for(Question q:ans){
            System.out.println("Question ID: "+q.getId());
            System.out.println("Content: "+q.getContent());
            System.out.println("Votes: "+q.getVotes().size());
            System.out.println("Replies:");
            Optional<List<Reply>> replies = questionService.getReply(q.getId());
            if(!replies.isEmpty()){
                for(Reply r:replies.get()){
                    System.out.println("  - User "+r.getUserId()+": "+r.getContent());
                }
            }
            else{
                continue;
            }
            System.out.println();
        }
    }
    
}
