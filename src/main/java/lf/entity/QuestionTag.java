package lf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
public class QuestionTag {

    @EmbeddedId
    //@GeneratedValue
    private QuestionTagId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id",nullable = false, insertable = false, updatable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id", nullable = false, insertable = false, updatable = false)
    private Tag tag;

    @Column(name = "createTime", insertable = true, updatable = false)
    private Timestamp createTime;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        QuestionTag questionTag = (QuestionTag) obj;
        return Objects.equals(question,questionTag.getQuestion())
                && Objects.equals(tag, questionTag.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(question,tag);
    }
}
