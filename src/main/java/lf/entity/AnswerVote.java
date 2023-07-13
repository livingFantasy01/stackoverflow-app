package lf.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AnswerVote {
    @EmbeddedId
    private QuestionVoteId id;

    private int vote;
}
