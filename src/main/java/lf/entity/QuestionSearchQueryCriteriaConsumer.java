package lf.entity;

import lf.dto.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;

    private CriteriaBuilder criteriaBuilder;

    private Root root;

    @Override
    public void accept(SearchCriteria searchCriteria) {

        if(searchCriteria.getOperation().equals("LIKE")){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(searchCriteria.getKey()),searchCriteria.getValue().toString()));
        }
        /*else if(searchCriteria.getOperation().equals("IN")){
           criteriaBuilder.createQuery().select(root).where(root.get(searchCriteria.getKey()).in(searchCriteria.getValue().toString()));
        }*/

    }



}
