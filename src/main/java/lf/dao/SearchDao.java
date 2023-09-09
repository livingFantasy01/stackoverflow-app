package lf.dao;

import lf.dto.QuestionSearchCriteriaDto;
import lf.dto.TagQuestionDto;
import lf.entity.Question;
import lf.entity.QuestionSearchQueryCriteriaConsumer;
import lf.enums.SortType;
import lf.repository.TagRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<TagQuestionDto> findQuestionCountByAllTags() {
        Query query = entityManager.createNativeQuery("select qt.tag_id, t.tag_name, count(qt.question_id) from question_tag qt inner join tag t on qt.tag_id = t.id group by qt.tag_id");
        List<Object[]> response = query.getResultList();
        List<TagQuestionDto> tagQuestionDtoList = new ArrayList<TagQuestionDto>();
        for (Object[] object : response) {
            tagQuestionDtoList.add(new TagQuestionDto(((BigInteger) object[0]).longValue(), (String) object[1], ((BigInteger) object[2])));
        }
        return tagQuestionDtoList;
    }
}
