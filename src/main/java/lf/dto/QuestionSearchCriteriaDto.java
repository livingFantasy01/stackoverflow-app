package lf.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionSearchCriteriaDto {

    private List<SearchCriteria> criteriaList;

    private List<OrderCriteria> orderCriteria;

    private int offset;
    private int count;

}
