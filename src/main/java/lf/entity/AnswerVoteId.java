package lf.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class AnswerVoteId implements Serializable {

    private static final long serialVersionUID = -3449034849368559355L;

    @ManyToOne
    @JoinColumn(name = "answer_id",nullable = false, insertable = false, updatable = false)
    private Answer answer;

    private Long userId;


}
