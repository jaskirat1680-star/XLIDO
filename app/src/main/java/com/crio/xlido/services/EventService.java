package com.crio.xlido.services;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.Question;
import com.crio.xlido.entities.User;
import com.crio.xlido.respositories.IEventRepository;
import com.crio.xlido.respositories.IQuestionRepository;
import com.crio.xlido.respositories.IUserRepository;

public class EventService {
    private IUserRepository userRepository;
    private IEventRepository eventRepository;
    private IQuestionRepository questionRepository;
    
    public EventService(IUserRepository userRepository, IEventRepository eventRepository, IQuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.questionRepository = questionRepository;
        
    }
    public Event createEvent(String title, Long organizerId){
        Optional<User> user = userRepository.findById(organizerId);
        if(user.isEmpty()){
            throw new RuntimeException("User with an id "+ organizerId+" does not exist");
        }
        Event event = new Event(organizerId, title);
        Event newEvent = eventRepository.save(event);
        return newEvent;
    }
    public Question addQuestion(Long eventId, Long userId, String content){
        Optional<Event> event = eventRepository.getById(eventId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new RuntimeException("User with an id "+ userId+" does not exist");
        }
        if(event.isEmpty()){
            throw new RuntimeException("Event with an id "+ eventId+" does not exist");
        }
        Question question = new Question(eventId, userId, content);
        Question newQuestion = questionRepository.save(question);

        List<Question> ques = event.get().getQuestions();
        ques.add(newQuestion);
        event.get().setQuestions(ques);
        return newQuestion;
    }
    public void deleteEvent(Long userId, Long eventId){
        Optional<Event> event = eventRepository.getById(eventId);
        if(event.isEmpty()){
            throw new RuntimeException("Event with an id "+ eventId+" does not exist");
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new RuntimeException("User with an id "+ userId+" does not exist");
        }
        if(event.get().getOrganizerId() != userId){
            throw new RuntimeException("User with an id "+userId+" is not a organizer of Event with an id "+eventId);
        }
        List<Question> questions = event.get().getQuestions();
        if(questions!=null){
            questions.forEach(q->questionRepository.delete(q.getId()));
        }
        eventRepository.deleteEvent(eventId);
    } 
    public void deleteQuestion(Long questionId, Long userId){
        Optional<Question> question = questionRepository.findByQuestionId(questionId);

        if(question.isEmpty()){
            throw new RuntimeException("Question with an id "+ questionId+" does not exist");
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new RuntimeException("User with an id "+ userId+" does not exist");
        }
        Optional<Event> event = eventRepository.getById((Long)question.get().getEventId());
        if(question.get().getUserId()!=user.get().getId()){
            throw new RuntimeException("User with an id "+ userId+" is not an author of question with an id "+questionId);
        }
        List<Question> questions = event.get().getQuestions();
        questions.removeIf(q->q.getId().equals(questionId));
        questionRepository.delete(questionId);
        event.get().setQuestions(questions);
    }
}
