package com.crio.xlido.respositories;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Question;

public class QuestionRepository implements IQuestionRepository{
    private final Map<Long, Question> storage = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);
    @Override
    public Question save(Question entity) {
        Question newQuestion = new Question(idCounter.incrementAndGet(), entity);
        storage.putIfAbsent(newQuestion.getId(), newQuestion);
        return newQuestion;
    }

    @Override
    public Optional<Question> findByQuestionId(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Question> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    @Override
    public void update(Long id, Question question) {
        storage.put(id, question);
    }
    
}
