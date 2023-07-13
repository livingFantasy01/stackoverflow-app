package lf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTagId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id",nullable = false, insertable = false, updatable = false)
    private Question question;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id",nullable = false, insertable = false, updatable = false)
    private Tag tag;

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false, insertable = false, updatable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", nullable = false, insertable = false, updatable = false)
    private Tag tag;*/

    /*@Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        QuestionTagId questionTagId = (QuestionTagId) obj;
        return Objects.equals(questionId,questionTagId.getQuestionId())
                && Objects.equals(tagId, questionTagId.getTagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId,tagId);
    }
*/
}
