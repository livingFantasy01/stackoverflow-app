package lf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagQuestionDto {
    private Long tagId;
    private String tagName;
    private BigInteger questionCount;
}
