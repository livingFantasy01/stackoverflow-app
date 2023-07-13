package lf.entity;

import lf.dto.AnswerDto;
import lf.dto.QuestionDto;
import lf.dto.TagDto;
import lf.enums.QuestionStatus;
import lf.enums.VoteType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId;
    private String title;
    private String body;

    private QuestionStatus status;
    private long voteCount;

    @Column(name = "createTime", insertable = true, updatable = false)
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "updateTime", insertable = true, updatable = true)
    @UpdateTimestamp
    private Timestamp updateTime;

    @OneToMany(mappedBy = "question", orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Answer> answers = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "question", orphanRemoval = true,fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<QuestionTag> questionTagList = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }

    public QuestionDto entityToDto(){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(id);
        questionDto.setUserId(userId);
        questionDto.setTitle(title);
        questionDto.setBody(body);
        questionDto.setQuestionStatus(status);
        questionDto.setVoteCount(voteCount);

/*        if(questionTagList != null){
            List<TagDto> tagDtoList =  questionTagList.stream().map(questionTag -> {
                Tag tag = questionTag.getTag();
                TagDto tagDto = new TagDto();
                tagDto.setId(tag.getId());
                tagDto.setTagName(tag.getTagName());
                return tagDto;
            }).collect(Collectors.toList());
            questionDto.setTagDtoList(tagDtoList);
        }*/

        return questionDto;
    }

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
        Question question = (Question) obj;
        return Objects.equals(this.getId(),question.getId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}


