package lf.dao;

import lf.constant.CommonConstants;
import lf.dto.QuestionDto;
import lf.entity.Answer;
import lf.entity.Question;
import lf.exception.BusinessException;
import lf.repository.QuestionRepository;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class QuestionDao {

    @Autowired
    private QuestionRepository questionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Question add(Question question) {
        //question.setVoteCount(0L);
        return questionRepository.save(question);
    }

    public Question findById(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        Question question = optionalQuestion.orElseThrow(() -> new BusinessException(String.format("No question present for Id %s", id)));
        return question;

    }

    public void update(QuestionDto questionDto) {
        int updateCount = questionRepository.setQuestionInfoById(questionDto.getTitle(), questionDto.getBody(), questionDto.getId());
        if (updateCount == 0) {
            throw new BusinessException(String.format("No question found for Id %s", questionDto.getId()));
        }
        System.out.println(String.format("%s questions updated successfully.", updateCount));
    }

    public void deleteById(Long id) {
        try {
            questionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println(emptyResultDataAccessException.getMessage());
            throw new BusinessException(String.format("No question found for Id %s", id));
        }
    }

    public List<Answer> fetchAnswersForQuestion(Question question, int offset, int limit){
        Query query = entityManager.createNamedQuery(CommonConstants.ANSWERS_FOR_QUESTION, Answer.class);
        query.setParameter("question",question);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        List<Answer> answerList = query.getResultList();

        return answerList;
    }
}
