package lf.dto;

import lf.enums.SortType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCriteria {

    private String key;
    private SortType sortType;
}
