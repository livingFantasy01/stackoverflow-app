package lf.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionVoteDto {

    private Long questionId;
    private Long userId;
    private int vote;
}
