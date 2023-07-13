package lf.dao;

import lf.dto.QuestionSearchCriteriaDto;
import lf.entity.Question;
import lf.entity.QuestionSearchQueryCriteriaConsumer;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class SearchDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Question> questionSearch(QuestionSearchCriteriaDto questionSearchCriteriaDto){
        int offset = 0;
        int count = 20;
        if(questionSearchCriteriaDto.getOffset() != 0){
            offset = questionSearchCriteriaDto.getOffset();
        }
        if(questionSearchCriteriaDto.getCount() != 0 && questionSearchCriteriaDto.getCount() <= 100){
            count = questionSearchCriteriaDto.getCount();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> questionRoot = criteriaQuery.from(Question.class);
        Predicate predicate = criteriaBuilder.conjunction();

        QuestionSearchQueryCriteriaConsumer consumer = new QuestionSearchQueryCriteriaConsumer(predicate,criteriaBuilder, questionRoot);
        questionSearchCriteriaDto.getCriteriaList().stream().forEach(consumer);
        predicate = consumer.getPredicate();
        criteriaQuery.where(predicate);
        List<Question> result = entityManager.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(count).getResultList();
        return result;
    }
}
