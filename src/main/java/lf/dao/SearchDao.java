package lf.dao;

import lf.dto.QuestionSearchCriteriaDto;
import lf.entity.Question;
import lf.entity.QuestionSearchQueryCriteriaConsumer;
import lf.enums.SortType;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Question> questionSearch(QuestionSearchCriteriaDto questionSearchCriteriaDto) {
        int offset = 0;
        int pageSize = 5;
        if (questionSearchCriteriaDto.getOffset() != 0) {
            offset = questionSearchCriteriaDto.getOffset();
        }
        if (questionSearchCriteriaDto.getCount() <= 500) {
            pageSize = questionSearchCriteriaDto.getCount();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> questionRoot = criteriaQuery.from(Question.class);

        QuestionSearchQueryCriteriaConsumer consumer = new QuestionSearchQueryCriteriaConsumer(new ArrayList<>(), criteriaBuilder, questionRoot);
        questionSearchCriteriaDto.getCriteriaList().stream().forEach(consumer);
        List<Predicate> predicates = consumer.getPredicates();
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        if (questionSearchCriteriaDto.getOrderCriteria() != null) {
            List<Order> orders = questionSearchCriteriaDto.getOrderCriteria().stream().map(orderCriteria -> {
                        Order order = null;
                        if (SortType.DESC.toString().equals(orderCriteria.getSortType().toString())) {
                            order = criteriaBuilder.desc(questionRoot.get(orderCriteria.getKey()));
                        } else if (SortType.ASC.toString().equals(orderCriteria.getSortType().toString())) {
                            order = criteriaBuilder.desc(questionRoot.get(orderCriteria.getKey()));
                        }
                        return order;
                    }
            ).collect(Collectors.toList());
            criteriaQuery.orderBy(orders);
        }

        List<Question> result = entityManager.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(pageSize).getResultList();
        return result;
    }

}
