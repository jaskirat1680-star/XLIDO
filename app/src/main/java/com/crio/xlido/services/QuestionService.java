package com.crio.xlido.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.Reply;
import com.crio.xlido.entities.User;
import com.crio.xlido.respositories.IEventRepository;
import com.crio.xlido.respositories.IQuestionRepository;
import com.crio.xlido.respositories.IReplyRepository;
import com.crio.xlido.respositories.IUserRepository;
import com.crio.xlido.utils.PopularQuestionComparator;

public class QuestionService {
    private IQuestionRepository questionRepository;
    private IReplyRepository replyRepository;
    private IUserRepository userRepository;
    private IEventRepository eventRepository;
    public QuestionService(IQuestionRepository questionRepository,
            IReplyRepository replyRepository, IUserRepository userRepository, IEventRepository eventRepository) {
        this.questionRepository = questionRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }
    public Reply addReply(Long questionId, Long userId, String content){
        Reply reply = new Reply(userId, content);
        replyRepository.add(questionId, reply);
        Optional<User> user = userRepository.findById(userId);
        Optional<Question> question = questionRepository.findByQuestionId(questionId);
        if(user.isEmpty()){
            throw new RuntimeException("User with an id "+ userId+" does not exist");
        }
        if(question.isEmpty()){
            throw new RuntimeException("Question with an id "+ questionId+" does not exist");

        }
        return reply;
    }
    public Optional<List<Reply>> getReply(Long quesId){
        return Optional.ofNullable(replyRepository.getReply(quesId));
    }
    public Question addUpVote(Long userId, Long questionId){
        Optional<Question> question = questionRepository.findByQuestionId(questionId);
        if(question.isEmpty()){
            throw new RuntimeException("Question with an id "+ questionId+" does not exist");
        }
        Question currQues = question.get();
        Set<Long> votes = currQues.getVotes();
        if(votes.contains(userId)){
            throw new RuntimeException("User with an id "+ userId+" has already upvoted a question with an id "+questionId);
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new RuntimeException("User with an id "+ userId+" does not exist");
        }
        votes.add(userId);
        currQues.setVotes(votes);
        questionRepository.update(questionId, currQues);
        return currQues;
    }
    public int totalVotes(Long questionId){
        Optional<Question> question = questionRepository.findByQuestionId(questionId);
        return question.get().getVotes().size();
    }
    public List<Question> listQuesitons(Long eventId, String sortingPreferString){
        List<Question> ques = questionRepository.getAll();
        Optional<Event> event = eventRepository.getById(eventId);
        if(event.isEmpty()){
            throw new RuntimeException("Event with an id "+ eventId+" does not exist");
        }
        List<Question> output = new ArrayList<>();
        for(Question q:ques){
            if(q.getEventId().equals(eventId)){
                output.add(q);
            }
        }
        if(sortingPreferString.equals("POPULAR")){
            output.sort(new PopularQuestionComparator());
        }
        else{
            Collections.reverse(output);
        }
        return output;
    }

}
