package com.crio.xlido.respositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Question;

public interface IQuestionRepository {
    Question save(Question question);
    Optional<Question> findByQuestionId(Long id);
    List<Question> getAll();
    void delete(Long Id);
    void update(Long id, Question question);

}
