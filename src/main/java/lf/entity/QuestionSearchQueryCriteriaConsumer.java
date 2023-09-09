package lf.entity;

import lf.dto.SearchCriteria;
import lf.enums.SearchCriteriaOperations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private List<Predicate> predicates;

    private CriteriaBuilder cb;

    private Root root;

    @Override
    public void accept(SearchCriteria searchCriteria) {
        if(searchCriteria.getOperation().equals(SearchCriteriaOperations.EQUAL.toString())){
            predicates.add(cb.equal(root.get(searchCriteria.getKey()),searchCriteria.getValue()));
        }
        else if(searchCriteria.getOperation().equals(SearchCriteriaOperations.NOT_EQUAL.toString())){
            predicates.add(cb.notEqual(root.get(searchCriteria.getKey()),searchCriteria.getValue()));
        }
        else if(searchCriteria.getOperation().equals(SearchCriteriaOperations.GREATER_THAN.toString())){
            predicates.add(cb.greaterThan(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString()));
        }
       else if(searchCriteria.getOperation().equals(SearchCriteriaOperations.LESS_THAN.toString())){
            predicates.add(cb.lessThan(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString()));
        }
        else if(searchCriteria.getOperation().equals(SearchCriteriaOperations.LIKE.toString())){
            predicates.add(cb.like(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString()));
        }

    }

}
