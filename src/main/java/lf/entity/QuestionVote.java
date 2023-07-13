package lf.entity;

import lf.enums.EntityType;
import lf.enums.VoteType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuestionVote implements Persistable<QuestionVoteId> {

    @EmbeddedId
    private QuestionVoteId id;

    private int vote;

    @Override
    public boolean isNew() {
        return true;
    }
}
