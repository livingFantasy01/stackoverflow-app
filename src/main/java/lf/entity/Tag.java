package lf.entity;

import lf.dto.AnswerDto;
import lf.dto.QuestionDto;
import lf.dto.TagDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagName;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tag")
    private List<QuestionTag> questionList;

    @Column(name = "createTime", insertable = true, updatable = false)
    private Timestamp createTime;

    @Column(name = "updateTime", insertable = true, updatable = true)
    private Timestamp updateTime;

    public TagDto entityToDto() {
        TagDto tagDto = new TagDto();
        tagDto.setId(id);
        tagDto.setUserId(userId);
        tagDto.setTagName(tagName);
        tagDto.setCreateTime(createTime);
        tagDto.setUpdateTime(updateTime);

        if (questionList != null) {
            List<QuestionDto> questionDtoList = questionList.stream().map(questionTag ->
                    {
                        QuestionDto questionDto = new QuestionDto();
                        Question question = questionTag.getQuestion();
                        questionDto.setId(question.getId());
                        questionDto.setUserId(question.getUserId());
                        questionDto.setTitle(question.getTitle());
                        questionDto.setBody(question.getBody());
                        return questionDto;
                    }
            ).collect(Collectors.toList());
            tagDto.setQuestionDtoList(questionDtoList);
        }
        return tagDto;
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
        Tag tag = (Tag) obj;
        return Objects.equals(this.getId(),tag.getId());

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
