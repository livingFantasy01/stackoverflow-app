package lf.entity;

import lf.constant.CommonConstants;
import lf.dto.AnswerDto;
import lf.dto.QuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NamedQueries(value = {
        @NamedQuery(name = CommonConstants.ANSWERS_FOR_QUESTION,
        query = "select a from Answer a where a.question = :question")

})
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    //@JoinColumn(name = "question_id")
    private Question question;
    private Long userId;
    private String title;
    private String body;

    @Column(name = "createTime", insertable = true, updatable = false)
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "updateTime", insertable = true, updatable = true)
    @UpdateTimestamp
    private Timestamp updateTime;

    public AnswerDto entityToDto(){
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(id);
        answerDto.setUserId(userId);
        answerDto.setTitle(title);
        answerDto.setBody(body);
        return answerDto;
    }

}
